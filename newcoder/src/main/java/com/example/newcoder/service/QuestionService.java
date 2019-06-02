package com.example.newcoder.service;

import com.example.newcoder.adapter.QuestionAdapter;
import com.example.newcoder.adapter.ScoreAdapter;
import com.example.newcoder.dao.AnswerDao;
import com.example.newcoder.dao.QuestionDao;
import com.example.newcoder.model.Answer;
import com.example.newcoder.model.Question;
import com.example.newcoder.model.Score;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    AnswerDao answerDao;

    public Question getQuestionbyId(int id)
    {
        Question question=questionDao.selectbyid(id);
        return question;//返回题目
    }

    public Answer getAnswerbyId(int id)
    {
        Answer answer=answerDao.selectbyid(id);
        return answer;
    }

    public List<Answer> getAnswerbyTypeId(int typeid)
    {
        List<Answer> answers=answerDao.selectbyTypeId(typeid,4);
        return answers;
    }

    public QuestionAdapter getQuestionAndAnswerbyId(int id)
    {
            int questionId = id;
            Question question = getQuestionbyId(questionId);
            List<Answer> answers = getAnswerbyTypeId(question.getId());//根据题号来分配答案
            QuestionAdapter adapter = new QuestionAdapter();
            adapter.setQuestion(question.getTitle());//设置题目内容
            adapter.setQuestionid(question.getId());//设置题目的id

            adapter.setAnswer(answers);

            return adapter;
    }
    public List<Question> getRandomQuestion(int num)//获取num个随机题目
    {
        List<Question> questions=questionDao.getRandomQuestion(num);
        return questions;
    }
    public List<QuestionAdapter> getRandomQuestionAndAnswer(int num)//分页查询
    {
        List<Question> questions=getRandomQuestion(num);
        List<QuestionAdapter> adapters=getQuestionAdapter(questions);
        return adapters;
    }

    public List<QuestionAdapter> getQuestionAdapter(List<Question> questions)//分页查询
    {
        List<QuestionAdapter> adapters=new ArrayList<>();
        for(Question question: questions) {
            QuestionAdapter adapter = new QuestionAdapter();
            adapter.setQuestionid(question.getId());
            adapter.setQuestion(question.getTitle());

            List<Answer> answers = getAnswerbyTypeId(question.getId());//根据题号来分配答案
            boolean flag=false;
            for(Answer elm : answers)
            {
                if(elm.getId()==question.getAnswerid())//若存在答案项
                {
                    flag=true;
                    break;
                }
            }
            if(!flag)//若答案不存在
            {
                Answer answer=getAnswerbyId(question.getAnswerid());
                //随机替换一个元素
                Random random=new Random();
                int index=random.nextInt(4);
                answers.get(index).setContent(answer.getContent());
                answers.get(index).setId(answer.getId());
                answers.get(index).setTypeid(answer.getTypeid());
            }

            adapter.setAnswer(answers);
            adapters.add(adapter);
        }
        return adapters;
    }

    public void handlestr(List<QuestionAdapter> adapters)
    {
        for(QuestionAdapter adapter:adapters)
        {
            adapter.setQuestion(adapter.getQuestion().replace('"','“'));
        }
    }
    public int CheckAnswer(Map<Integer,Integer> maps)
    {
        int score=0;//分数
        for(Map.Entry<Integer,Integer> map : maps.entrySet())
        {
            Question question=getQuestionbyId(map.getKey());//根据id查询题目
            Answer answer=getAnswerbyId(question.getAnswerid());//根据题目的答案id来查询答案
            if(answer.getId()==map.getValue())//若id相同说明答案正确
            {
                score++;//成绩+1
            }
        }
        return score;
    }
    public ScoreAdapter CheckAnswerReturnDetail(List<Answer> answers)
    {
        int count=0;
        List<Score> scores=new ArrayList<>();
        ScoreAdapter scoreAdapter=new ScoreAdapter();
        for(Answer answer:answers)
        {
            Score score=new Score();
            Question question=getQuestionbyId(answer.getTypeid());//根据题目的答案id来查询答案
            if(question!=null&&question.getAnswerid()==answer.getId())
            {
                count++;
                score.setIsright(true);
            }else
            {
                score.setIsright(false);
            }
            score.setQuestionid(answer.getTypeid());
            score.setAnswerid(answer.getId());
            scores.add(score);
        }
        scoreAdapter.setScore(count);
        scoreAdapter.setScores(scores);
        return scoreAdapter;
    }
    public boolean AddQuestion(String title,String content,String contents)
    {
        try {
            Question question = new Question();
            question.setTitle(title);
            questionDao.InsertQuestionNoAnswerID(question);//插入题目

            String[] answer_contents = contents.split("\r\n");//分隔出干扰项
            for (int i = 0; i < answer_contents.length; i++) {
                Answer answer = new Answer();
                answer.setContent(answer_contents[i]);
                int max = questionDao.getMaxQuestionId();//获取最大题号
                answer.setTypeid(max);
                answerDao.InsertAnswer(answer);
            }
            //插入正确答案
            Answer answer = new Answer();
            answer.setContent(content);
            int max = questionDao.getMaxQuestionId();//获取最大题号
            answer.setTypeid(max);
            answerDao.InsertAnswer(answer);

            int max_answer = answerDao.getMaxAnswerId();//获取最大的答案
            question.setAnswerid(max_answer);
            questionDao.UpdateQuestion(question);//更新题目

            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
