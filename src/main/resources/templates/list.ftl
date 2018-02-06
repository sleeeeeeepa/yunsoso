<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>云搜搜</title>

    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap-combined.min.css" rel="stylesheet">
    <link href="/static/css/pagination.css" rel="stylesheet">

</head>
<body>
<form id="result_list" action="">

    <#if kw??>
        <input type="hidden" value="${kw}" id="kw"/>

         <div onclick="reserve()" style="float: right;">
             <a  href="javascript:void(0);">结果不理想？戳此预约！</a>
         </div>
    </#if>


    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>
                            名称
                        </th>
                        <th>
                            大小
                        </th>
                        <th>
                            url
                        </th>
                        <th>
                            来源关键字
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list  results  as  item>
                    <tr>
                        <td>
                        ${item.yun_fileName}
                        </td>
                        <td>
                        ${item.yun_sizeFormat}
                        </td>
                        <td>
                            <a href="${item.yun_url}">${item.yun_url}</a>
                        </td>
                        <td>
                        ${item.yun_keyWord}
                        </td>
                    </tr>
                    </#list>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</form>
<div id="Pagination" class="pagination" style="width:100% ;align:center;"><!-- 这里显示分页 --></div>

<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="/static/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<!-- 包括所有已编译的插件 -->
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap.bundle.min.js"></script>
<script src="/static/js/jquery.pagination.js"></script>

<script type="text/javascript">
    $(function(){
        var currpage = ${currentPage};//当前页
        var initPagination = function() {
            var num_entries = ${totalItem};//总条数
            // 创建分页
            $("#Pagination").pagination(num_entries, {
                num_edge_entries: 1, //边缘页数
                num_display_entries: 10, //主体页数
                callback: pageselectCallback,
                items_per_page:20, //每页显示10项
                current_page:currpage,
                prev_text:"上一页",
                next_text:"下一页"
            });
        }();

        function pageselectCallback(page_index, jq){
            if(page_index!=currpage){
                var kw = $("#kw").val();
                if(kw==undefined){
                    window.location.href="/findAll/"+page_index;
                }else {
                    document.getElementById('result_list').action = "/find/"+kw+"/"+page_index;
                    $('#result_list').submit();
                }
            }
            return false;
        }
    });

    function reserve() {
        $.ajax({
            type: "post",
            url: "/reserve/${kw}",
            data:{},
            dataType: "text",
            async : false,
            success: function(data){
                if(data=='suc'){	//未储存
                    alert("【${kw}】预约成功！")
                }else{
                    alert("预约失败")
                }
            },
            error: function(data) {
                alert(data)
            }
        });
    }
</script>



</body>
</html>