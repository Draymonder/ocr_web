$(function() {

    myDate = new Date();
    $currentYear = $(".date_year");
    $currentMonth = $(".date_month");
    $currentDate = $('.date_day');
    $currentYear.text(myDate.getFullYear());
    $currentMonth.text(parseInt(myDate.getMonth() + 1));
    $currentDate.text(myDate.getDate());

    

    //卡券
    var kqPic = ['images/hezuoquan_05.png', 'images/hezuoquan_08.png', 'images/hezuoquan_10.png', 'images/hezuoquan_03.png'];
    var kqPicHover = ['images/hezuoquan_hover_05.png', 'images/hezuoquan_hover_08.png', 'images/hezuoquan_hover_10.png', 'images/hezuoquan_hover_03.png'];

    $('.coupon_xiugai_kaquan').mouseover(function () {
        $('.coupon_xiugai_kaquan').each(function (i, e) {
            $(e).find('.coupon_xiugai_img img').attr('src', kqPic[i]);
        });
        $(this).find('.coupon_xiugai_img img').attr('src', kqPicHover[$(this).index()]);
        $('.coupon_xiugai_kaquan').removeClass('active');
        $(this).addClass('active');

    })

    $('.coupon_xiugai_kaquan').mouseout(function () {
        $(this).find('.coupon_xiugai_img img').attr('src', kqPic[$(this).index()]);
        $('.coupon_xiugai_kaquan').removeClass('active');


    })

    //弹窗
    $('.close').click(function () {
        $('.mask').addClass('hide');
        $('.coupon_tanckuang').addClass('hide');
        $('.coupon_tanckuang1').addClass('hide');
        $('.coupon_tanckuang_shenhe').addClass('hide');
        $('.coupon_tanckuang_chehui').addClass('hide');
        $('.coupon_tanckuang_jindu').addClass('hide');
        $('.bai_menu').addClass('hide');
    })
    $('.popbox_close').click(function () {
    	$('.mask').hide();
    	$('#preView').hide();
    })
    $('.coupon_quxiao').click(function () {
        $('.mask').addClass('hide');
        $('.coupon_tanckuang').addClass('hide');
        $('.coupon_tanckuang1').addClass('hide');
        $('.coupon_tanckuang_shenhe').addClass('hide');
    })
    $('.coupon_queren').click(function () {
        $('.mask').addClass('hide');
        $('.coupon_tanckuang').addClass('hide');
        $('.coupon_tanckuang_shenhe').addClass('hide');
    })
    //用于刷新缓存不隐藏mask
    $('.coupon_queren_mask').click(function () {
        $('.coupon_tanckuang').addClass('hide');
        $('.coupon_tanckuang_shenhe').addClass('hide');
    })
    
    $('.bai_quxiao').click(function () {
        $('.mask').addClass('hide');
        $('.bai_menu').addClass('hide');
    })


    $('.common_list').click(function () {
     if ($(this).attr('class') == 'common_list') {
            $(this).addClass('cur');
            $(this).find('.curIcon').show();
            $(this).parents('.fuquan_box').find('.quansheng').removeClass('cur');
            $(this).parents('.fuquan_box').find('.quansheng').find('.curIcon').hide();
        } else {
            $(this).removeClass('cur');
            $(this).find('.curIcon').hide();
        }       
   })

	    $('.quansheng').click(
			function() {
				if ($(this).attr('class') == 'quansheng') {
					$(this).addClass('cur');
					$(this).find('.curIcon').show()
					$(this).parents('.fuquan_box').find('.common_list')
							.removeClass('cur');
					$(this).parents('.fuquan_box').find('.common_list').find(
							'.curIcon').hide();
					$(this).parents('.fuquan_box').find('.common_list_yw')
					.removeClass('cur');
			        $(this).parents('.fuquan_box').find('.common_list_yw').find(
					'.curIcon').hide();
				} else {
					$(this).removeClass('cur');
					$(this).find('.curIcon').hide();
				}
			})



    $('.common_chongzhi').click(function () {

        $('.common_chongzhi').removeClass('cur');
        $(this).addClass('cur');
        $('.icon').hide();
        $('.icon').eq($(this).index()).show();
    })


    $('.common_zhifu').click(function () {
        $('.common_zhifu').removeClass('cur');
        $(this).addClass('cur');
        $('.icon2').hide();
        $(this).find('.icon2').show();
        $('.qipao').hide();
        $(this).find(".qipao").show();
    })
    
    
    
    //子券支付方式单选
    $('.common_list_zhifu').click(function() {
        $('.common_list_zhifu').removeClass('cur');
        $(this).addClass('cur');
        $('.icon_zhifu').hide();
        $('.icon_zhifu').eq($(this).index()).show();
    })


    $('.common_changjing').click(function () {

        $('.common_changjing').removeClass('cur');
        $(this).addClass('cur');
        $('.changjing').hide();
        $('.changjing').eq($(this).index()).show();
    })


    $('.brand_new').click(function () {
        $('.brand_new').removeClass('cur');
        $(this).addClass('cur');
        $('.tp').hide();
        $('.tp').eq($(this).index()).show();
    })


    $('.common_list1').click(function () {

        if ($(this).hasClass('cur')) {
            $(this).removeClass('cur');
            $(this).find('.curIcon').hide();
            $('.dangci,.fangshi').hide();
            $(this).parents('.changjing_list').find('.ll,.jf,.eb,.yw,.hd').removeClass('cur');
            $(this).parents('.changjing_list').find('.ll,.jf,.eb,.yw,.hd').find('.curIcon').hide();
        }else{
            $(this).addClass('cur');
            $(this).find('.curIcon').show();
            $('.dangci,.fangshi').show();
            $(this).parents('.changjing_list').find('.ll,.jf,.eb,.yw,.hd').removeClass('cur');
            $(this).parents('.changjing_list').find('.ll,.jf,.eb,.yw,.hd').find('.curIcon').hide();
        }
    })


    $('.common_list2').click(function () {
        if ($(this).hasClass('cur')) {
            $(this).removeClass('cur');
            $(this).find('.curIcon').hide();
            $(this).parents('.fuquan_box').find('.cz,.jf,.eb,.yw,.hd').removeClass('cur');
            $(this).parents('.fuquan_box').find('.cz,.jf,.eb,.yw,.hd').find('.curIcon').hide();
            $('.dangci,.fangshi').hide()
            $('.koujian').hide();
        }else{
            $(this).addClass('cur');
            $(this).find('.curIcon').show();
            $('.dangci,.fangshi').show();
            $(this).parents('.fuquan_box').find('.cz,.jf,.eb,.yw,.hd').removeClass('cur');
            $(this).parents('.fuquan_box').find('.cz,.jf,.eb,.yw,.hd').find('.curIcon').hide();
            $('.dangci,.fangshi').hide()
            $('.koujian').hide();
        }
    })


    $('.common_list3').click(function () {
        if ($(this).hasClass('cur')) {
            $(this).removeClass('cur');
            $(this).find('.curIcon').hide();
            $('.koujian').show();
            $(this).parents('.fuquan_box').find('.ll,.cz,.eb,.yw,.hd').removeClass('cur');
            $(this).parents('.fuquan_box').find('.ll,.cz,.eb,.yw,.hd').find('.curIcon').hide();
        }else{
            $(this).addClass('cur');
            $(this).find('.curIcon').show();
            $('.koujian').show();
            $(this).parents('.fuquan_box').find('.ll,.cz,.eb,.yw,.hd').removeClass('cur');
            $(this).parents('.fuquan_box').find('.ll,.cz,.eb,.yw,.hd').find('.curIcon').hide();
        }
    })


    $('.common_list4').click(function () {
        if ($(this).hasClass('cur')) {
            $(this).removeClass('cur');
            $(this).find('.curIcon').hide();
            $('.koujian').show();
            $(this).parents('.fuquan_box').find('.ll,.cz,.jf,.yw,.hd').removeClass('cur');
            $(this).parents('.fuquan_box').find('.ll,.cz,.jf,.yw,.hd').find('.curIcon').hide();

        }else{
            $(this).addClass('cur');
            $(this).find('.curIcon').show();
            $('.koujian').show();
            $(this).parents('.fuquan_box').find('.ll,.cz,.jf,.yw,.hd').removeClass('cur');
            $(this).parents('.fuquan_box').find('.ll,.cz,.jf,.yw,.hd').find('.curIcon').hide();

        }
    })
    
    $('.common_list5').click(function () {
        if ($(this).hasClass('cur')) {
            $(this).removeClass('cur');
            $(this).find('.curIcon').hide();
            $('.koujian').show();
            $(this).parents('.fuquan_box').find('.ll,.cz,.jf,.eb,.hd').removeClass('cur');
            $(this).parents('.fuquan_box').find('.ll,.cz,.jf,.eb,.hd').find('.curIcon').hide();

        }else{
            $(this).addClass('cur');
            $(this).find('.curIcon').show();
            $('.koujian').show();
            $(this).parents('.fuquan_box').find('.ll,.cz,.jf,.eb,.hd').removeClass('cur');
            $(this).parents('.fuquan_box').find('.ll,.cz,.jf,.eb,.hd').find('.curIcon').hide();

        }
    })
    
    $('.common_list6').click(function () {
        if ($(this).hasClass('cur')) {
            $(this).removeClass('cur');
            $(this).find('.curIcon').hide();
            $('.koujian').show();
            $(this).parents('.fuquan_box').find('.ll,.cz,.jf,.yw,.eb').removeClass('cur');
            $(this).parents('.fuquan_box').find('.ll,.cz,.jf,.yw,.eb').find('.curIcon').hide();

        }else{
            $(this).addClass('cur');
            $(this).find('.curIcon').show();
            $('.koujian').show();
            $(this).parents('.fuquan_box').find('.ll,.cz,.jf,.yw,.eb').removeClass('cur');
            $(this).parents('.fuquan_box').find('.ll,.cz,.jf,.yw,.eb').find('.curIcon').hide();

        }
    })


//    $('.coupon_search_info').focus(function()
//    {
//        $('.coupon_search_info').val('');
//    })
//    $('.coupon_search_info').blur(function()
//    {
//    	if($('.coupon_search_info').val()==""){
//    		$('.coupon_search_info').val('请输入父券名称');
//    	}
//    })
    
    $('.coupon_tankuang_shenhe_text').focus(function()
    {
        $('.coupon_tankuang_shenhe_text').html('');
    })
    $('.coupon_tankuang_shenhe_text').blur(function()
    {
    	if($('.coupon_tankuang_shenhe_text').html()==""){
    		$('.coupon_tankuang_shenhe_text').html('审核未通过原因……');
    	}
    })
    
    
    $('.coupon_tankuang_chehuei_text').focus(function()
    {
        $('.coupon_tankuang_chehuei_text').html('');
    })
    $('.coupon_tankuang_chehuei_text').blur(function()
    {
    	if($('.coupon_tankuang_chehuei_text').html()==""){
    		$('.coupon_tankuang_chehuei_text').html('申请撤回原因……');
    	}
    })
    
    
    
    $('.quanxian_anniu').click(function (){
        if($(this).hasClass('cked')){
            $(this).parents('.quanxian_tianjia').find('.quanxian_zhankai').slideUp('slow');
            $(this).removeClass('cked');
        }else{
            $('.quanxian_tianjia .quanxian_zhankai').hide();
            $('.quanxian_tianjia .quanxian_anniu').removeClass('cked');

            $(this).parents('.quanxian_tianjia').find('.quanxian_zhankai').slideDown('slow');
            $(this).addClass('cked');
        }

    });

    //下拉内容点击
    $('.quanxian_zhankai li').click(function (){
        $(this).parents('.quanxian_tianjia').find('.quanxian_input').text($(this).text());
        $(this).parents('.quanxian_zhankai').slideUp();
        $(this).parents('.quanxian_tianjia').find('.quanxian_anniu').removeClass('cked');
    });
    
    //下拉内容点击
    $('.kaquan_anniu').click(function (){
        if($(this).hasClass('cked')){
            $(this).parents('.kaquan_tianjia').find('.kaquan_zhankai').slideUp('slow');
            $(this).removeClass('cked');
        }else{
            $('.kaquan_tianjia .kaquan_zhankai').hide();
            $('.kaquan_tianjia .kaquan_anniu').removeClass('cked');

            $(this).parents('.kaquan_tianjia').find('.kaquan_zhankai').slideDown('slow');
            $(this).addClass('cked');
        }

    });
    $('.kaquan_zhankai li').click(function (){
        $(this).parents('.kaquan_tianjia').find('.kaquan_input').text($(this).text());
        $(this).parents('.kaquan_zhankai').slideUp();
        $(this).parents('.kaquan_tianjia').find('.kaquan_anniu').removeClass('cked');
    });
    
    //控制子券奖品码选择
    $('.common_chongzhi_prize').click(function () {
    	$('.common_chongzhi_prize').removeClass('cur');
    	$(this).addClass('cur');
    	$('.icon_prize').hide();
    	$('.icon_prize').eq($(this).index()).show();
    });
    
    
    //用于父券领取场景互斥
    
    $('.common_list_yw').click(function () {

    	$(this).parents('.fuquan_box').find('.quansheng').removeClass('cur');
        $(this).parents('.fuquan_box').find('.quansheng').find('.curIcon').hide();
        var curr = $(this);
        var name = curr.attr('name');
        var index = curr.index();
        var options = curr.parent('ul').find('.curIcon');

        var clicked = curr.hasClass('cur');
        var siblings = curr.parent('ul').find('[name="' + name + '"]');

        siblings.removeClass('cur');
        curr[clicked ? 'removeClass' : 'addClass']('cur');
        siblings.find('.curIcon').hide();
        options.eq(index)[clicked ? 'hide' : 'show']();

    });

  //用于批量发券启用，不启用
    $('.common_list_plfqqy').click(function () {
    	$(this).parents('.fuquan_box').find('.common_list_nplfqqy').removeClass('cur');
    	$(this).parents('.fuquan_box').find('.common_list_nplfqqy').find('.curIcon').hide();
//    	$(this).parents('.fuquan_box').find('.card_center').hide();
    	if($(this).hasClass('cur')){
    		$(".second").hide();
        	$(".three").hide();
    	}
    	else{
    		$(".second").show();
        	$(".three").hide();
    	}
    	$(".send_card_way").show();
        $(this).parents('.other_way').hide();
        var curr = $(this);
        var name = curr.attr('name');
        var index = curr.index();
        var options = curr.parent('ul').find('.curIcon');

        var clicked = curr.hasClass('cur');
        var siblings = curr.parent('ul').find('[name="' + name + '"]');

        siblings.removeClass('cur');
        curr[clicked ? 'removeClass' : 'addClass']('cur');
        siblings.find('.curIcon').hide();
        options.eq(index)[clicked ? 'hide' : 'show']();

    });
    $('.common_list_nplfqqy').click(function () {
    	$(this).parents('.fuquan_box').find('.common_list_plfqqy').removeClass('cur');
    	$(this).parents('.fuquan_box').find('.common_list_plfqqy').find('.curIcon').hide();
    	if($(this).hasClass('cur')){
    		$(".second").hide();
        	$(".three").hide();
    	}
    	else{
    		$(".second").hide();
        	$(".three").show();
    	}
    	var curr = $(this);
        var name = curr.attr('name');
        var index = curr.index();
        var options = curr.parent('ul').find('.curIcon');

        var clicked = curr.hasClass('cur');
        var siblings = curr.parent('ul').find('[name="' + name + '"]');

        siblings.removeClass('cur');
        curr[clicked ? 'removeClass' : 'addClass']('cur');
        siblings.find('.curIcon').hide();
        options.eq(index)[clicked ? 'hide' : 'show']();

    });
  // 发券方式
    $('.common_list_all').click(function () {

    	$(this).parents('.fuquan_box').find('.common_list_add').removeClass('cur');
    	$(this).parents('.fuquan_box').find('.common_list_add').find('.curIcon').hide();
        var curr = $(this);
        var name = curr.attr('name');
        var index = curr.index();
        var options = curr.parent('ul').find('.curIcon');

        var clicked = curr.hasClass('cur');
        var siblings = curr.parent('ul').find('[name="' + name + '"]');

        siblings.removeClass('cur');
        curr[clicked ? 'removeClass' : 'addClass']('cur');
        siblings.find('.curIcon').hide();
        options.eq(index)[clicked ? 'hide' : 'show']();

    });
    $('.common_list_add').click(function () {

    	$(this).parents('.fuquan_box').find('.common_list_all').removeClass('cur');
    	$(this).parents('.fuquan_box').find('.common_list_all').find('.curIcon').hide();

        var curr = $(this);
        var name = curr.attr('name');
        var index = curr.index();
        var options = curr.parent('ul').find('.curIcon');

        var clicked = curr.hasClass('cur');
        var siblings = curr.parent('ul').find('[name="' + name + '"]');

        siblings.removeClass('cur');
        curr[clicked ? 'removeClass' : 'addClass']('cur');
        siblings.find('.curIcon').hide();
        options.eq(index)[clicked ? 'hide' : 'show']();

    });
})







 






