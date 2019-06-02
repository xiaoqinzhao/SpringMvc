(function (window, undefined) {
    var PopupAdd = Base.getClass('main.component.PopupAdd');
    var PopupMsg = Base.getClass('main.component.PopupMsg');

    Base.ready({
        initialize: fInitialize,
        binds: {
            'click #zu-top-add-question': fClickAdd,
            'click #zh-top-nav-count-wrap': fClickMsg,
            'click #zh-load-more': ffmore
        }
    });

    function fInitialize() {
        var that = this;
    }

    function fClickAdd() {
        var that = this;
        PopupAdd.show({
            ok: function () {
                window.location.replace("/");
            }
        });
    }

    function fClickMsg() {
            var that = this;
            PopupMsg.show({
                ok: function () {
                    window.location.replace("/msg/list");
                }
            });
        }

    //时间转换
    function   formatDate(now)   {
        var   now= new Date(now);
        var   year=now.getFullYear();
        var   month=now.getMonth()+1;
        var   date=now.getDate();
        var   hour=now.getHours();
        var   minute=now.getMinutes();
        var   second=now.getSeconds();
        return   year+"-"+fixZero(month,2)+"-"+fixZero(date,2)+" "+fixZero(hour,2)+":"+fixZero(minute,2)+":"+fixZero(second,2);
    }
//时间如果为单位数补0
    function fixZero(num,length){
        var str=""+num;
        var len=str.length;     var s="";
        for(var i=length;i-->len;){
            s+="0";
        }
        return s+str;
    }

    function ffmore(oEvent) {
        var oEl = $(oEvent.currentTarget);
        var sId = $.trim(oEl.attr('data-id'));
        var stip= $.trim(oEl.attr('data-tip'));
        if (!sId) {
            return;
        }
        $.post("/question/more",{
          offset:  sId,
            except:stip
        },
             function (oResult) {
                 oResult=$.parseJSON(oResult);
                 if (oResult.code === 999) {
                     alert('请登录后再操作');
                     window.location.href = '/reglogin?next=' + window.decodeURIComponent(window.location.href);
                 } else if(oResult.code === 1){
                     alert('出现错误，请重试');
                 }else{
                    var vos=$.parseJSON(oResult.msg);//解析json
                     if(vos.length<=0){
                         $('#tip').text("没有更多内容了");
                         return ;
                     }
                     $('#tip').text("");
                    for(var i=0;i<vos.length;i++)
                    {
                        var vo=vos[i];
                        sId=parseInt(sId);
                        sId=sId+1;
                        stip=stip+","+vo.question.id;

                        $('#js-home-feed-list').append("<div class='feed-item folding feed-item-hook feed-item-2\n" +
                            "                        ' feed-item-a='' data-type='a' id='feed-2' data-za-module='FeedItem' data-za-index=''>\n" +
                            "                            <meta itemprop='ZReactor' data-id='389034' data-meta='{&quot;source_type&quot;: &quot;promotion_answer&quot;, &quot;voteups&quot;: 4168, &quot;comments&quot;: 69, &quot;source&quot;: []}'>\n" +
                            "                            <div class='feed-item-inner'>\n" +
                            "                                <div class='avatar'>\n" +
                            "                                    <a title='"+vo.user.name+"' data-tip='p$t$amuro1230' class='zm-item-link-avatar' target='_blank' href='https://nowcoder.com/people/amuro1230'>\n" +
                            "                                        <img src='"+vo.user.headUrl+"' class='zm-item-img-avatar'></a>\n" +
                            "                                </div>\n" +
                            "                                <div class='feed-main'>\n" +
                            "                                    <div class='feed-content' data-za-module='AnswerItem'>\n" +
                            "                                        <meta itemprop='answer-id' content='389034'>\n" +
                            "                                        <meta itemprop='answer-url-token' content='13174385'>\n" +
                            "                                        <h2 class='feed-title'>\n" +
                            "                                            <a class='question_link' href='/question/"+vo.question.id+"'>"+vo.question.title+"</a></h2>\n" +
                            "                                        <div class='feed-question-detail-item'>\n" +
                            "                                            <div class='question-description-plain zm-editable-content'></div>\n" +
                            "                                        </div>\n" +
                            "                                        <div class='expandable entry-body'>\n" +
                            "                                            <div class='zm-item-vote'>\n" +
                            "                                                <a class='zm-item-vote-count js-expand js-vote-count' href='javascript:;' data-bind-votecount=''>4168</a></div>\n" +
                            "                                            <div class='zm-item-answer-author-info'>\n" +
                            "                                                <a class='author-link' data-tip='p$b$amuro1230' target='_blank' href='/user/$!{vo.user.id}'>"+vo.user.name+"</a>\n" +
                            "                                                ，"+formatDate(vo.question.createdDate)+"</div>\n" +
                            "                                            <div class='zm-item-vote-info' data-votecount='4168' data-za-module='VoteInfo'>\n" +
                            "                                                <span class='voters text'>\n" +
                            "                                                    <a href='#' class='more text'>\n" +
                            "                                                        <span class='js-voteCount'>4168</span>&nbsp;人赞同</a></span>\n" +
                            "                                            </div>\n" +
                            "                                            <div class='zm-item-rich-text expandable js-collapse-body' data-resourceid='123114' data-action='/answer/content' data-author-name='李淼' data-entry-url='/question/19857995/answer/13174385'>\n" +
                            "                                                <div class='zh-summary summary clearfix'>"+vo.question.content+"</div>\n" +
                            "                                            </div>\n" +
                            "                                        </div>\n" +
                            "                                        <div class='feed-meta'>\n" +
                            "                                            <div class='zm-item-meta answer-actions clearfix js-contentActions'>\n" +
                            "                                                <div class='zm-meta-panel'>\n" +
                            "                                                    <a data-follow='q:link' class='follow-link zg-follow meta-item' href='javascript:;' id='sfb-123114'>\n" +
                            "                                                        <i class='z-icon-follow'></i>关注问题</a>\n" +
                            "                                                    <a href='/question/"+vo.question.id+"' name='addcomment' class='meta-item toggle-comment js-toggleCommentBox'>\n" +
                            "                                                        <i class='z-icon-comment'></i>"+vo.question.commentCount+" 条评论</a>\n" +
                            "\n" +
                            "\n" +
                            "                                                    <button class='meta-item item-collapse js-collapse'>\n" +
                            "                                                        <i class='z-icon-fold'></i>收起</button>\n" +
                            "                                                </div>\n" +
                            "                                            </div>\n" +
                            "\n" +
                            "                                        </div>\n" +
                            "                                    </div>\n" +
                            "                                </div>\n" +
                            "                            </div>\n" +
                            "                        </div>");
                    }
                     oEl.attr('data-id',sId);
                     oEl.attr('data-tip',stip);
                }
            }
        )
    }

})(window);