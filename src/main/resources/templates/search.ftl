<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>云搜搜</title>

<link rel="stylesheet" type="text/css" href="/static/css/default.css" />

<!--必要样式-->
<link rel="stylesheet" type="text/css" href="/static/css/search-form.css" />

</head>
<body>
<#--  搜索主体 -->
<form onsubmit="submitFn(this, event);" action="" id="search">
	<div class="search-wrapper">
		<div class="input-holder">
			<input type="text" class="search-input" placeholder="请输入关键词" id="kw"/>
			<button class="search-icon" onClick="searchToggle(this, event);"><span></span></button>
		</div>
		<button onClick="findAll();" style="background: #fff;color: #000;height: 30px;line-height: 27px;width: 130px;border-radius: 10px;text-align: center;margin: 0 auto;margin-top: 5px;">
			看看有什么
		</button>
		<span class="close" onClick="searchToggle(this, event);"></span>
		<div class="result-container">

		</div>
	</div>
</form>


<script src="/static/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
function searchToggle(obj, evt){
	var container = $(obj).closest('.search-wrapper');

	if(!container.hasClass('active')){
		  container.addClass('active');
		  evt.preventDefault();
	}
	else if(container.hasClass('active') && $(obj).closest('.input-holder').length == 0){
		  container.removeClass('active');
		  // clear input
		  container.find('.search-input').val('');
		  // clear and hide result container when we press close
		  container.find('.result-container').fadeOut(100, function(){$(this).empty();});
	}

}

function submitFn(obj, evt){
	value = $(obj).find('.search-input').val().trim();

	_html = "您搜索的关键词： ";
	if(!value.length){
		_html = "关键词不能为空。";
	} else{
        $.ajax({
            type: "post",
            url: "/checkThisKeyword/"+value,
			data:{},
            dataType: "text",
            async : false,
            success: function(data){
				if(data=='0'){	//未储存
				    alert("抱歉【"+value+"】未储存，已为您加入查询队列，明天再来看看吧！")
				}else{
				    alert();
                    var rootPath = getRootPath();
				    window.open("/find/"+value+"/0");
            }
            },
            error: function(data) {
				alert(data)
            }
        });
        return false;
	}

	$(obj).find('.result-container').html('<span>' + _html + '</span>');
	$(obj).find('.result-container').fadeIn(100);

	evt.preventDefault();
}

function findAll() {
    window.location.href="/findAll/0";
}


function getRootPath() {
    //获取当前网址，如： http://localhost:80/ybzx/index.jsp
    var curPath = window.document.location.href;
    //获取主机地址之后的目录，如： ybzx/index.jsp
    var pathName = window.document.location.pathname;
    var pos = curPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:80
    return curPath.substring(0, pos);
}
</script>

</body>
</html>