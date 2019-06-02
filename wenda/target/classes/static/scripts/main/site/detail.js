(function (window, undefined) {
    var Action = Base.createClass('main.util.Action');

    Base.ready({
        initialize: fInitialize,
        // 事件代理
        events: {
            'click .js-like': fVote,
            'click .js-dislike': fVote,
            'click #focus': ffocus,
            'click #unfocus': ffocus
        }
    });

    function fInitialize() {
        var that = this;
    }

    function fVote(oEvent) {
        var that = this;
        var oEl = $(oEvent.currentTarget);
        var oDv = oEl.closest('div.js-vote');
        var sId = $.trim(oDv.attr('data-id'));
        var bLike = oEl.hasClass('js-like');
        if (!sId) {
            return;
        }
        if (that.isVote) {
            return;
        }
        that.isVote = true;
        Action[bLike ? 'like' : 'dislike']({
            commentId: sId,
            call: function (oResult) {
                // 调整样式
                oDv.find('.pressed').removeClass('pressed');
                oDv.find(bLike ? '.js-like' : '.js-dislike').addClass('pressed');
                // 更新数量
                oDv.closest('div.js-comment').find('span.js-voteCount').html(oResult.msg);
            },
            error: function (oResult) {
                if (oResult.code === 999) {
                    alert('请登录后再操作');
                    window.location.href = '/reglogin?next=' + window.decodeURIComponent(window.location.href);
                } else {
                    alert('出现错误，请重试');
                }
            },
            always: function () {
                that.isVote = false;
            }
        });
    }

    function ffocus(oEvent) {
        var that = this;
        var oEl = $(oEvent.currentTarget);
        var oDv = $('#focusspace');
        var sId = $.trim(oDv.attr('data-id'));
        var bFocus = oEl.hasClass('js-focused');
        if (!sId) {
            return;
        }
        Action[bFocus ? 'focus' : 'unfocus']({
            questionId: sId,
            call: function (oResult) {
                // 调整样式
                oDv.find('.hidden').removeClass('hidden');
                oDv.find(bFocus ? '.js-focused' : '.js-unfocused').addClass('hidden');
                // 更新数量
                $('#focuscount').html(oResult.msg);
            },
            error: function (oResult) {
                if (oResult.code === 999) {
                    alert('请登录后再操作');
                    window.location.href = '/reglogin?next=' + window.decodeURIComponent(window.location.href);
                } else {
                    alert('出现错误，请重试');
                }
            },
            always: function () {
            }
        });
    }


    function fUnlike(oEvent) {
        var that = this;
        var oEl = $(oEvent.currentTarget);

    }

})(window);