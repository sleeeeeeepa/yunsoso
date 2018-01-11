<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>云搜搜</title>

    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap-combined.min.css" rel="stylesheet">

</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <table class="table">
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
                        ${item.yun_url}
                        </td>
                    </tr>
                </#list>

                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="/static/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<!-- 包括所有已编译的插件 -->
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap.bundle.min.js"></script>

</body>
</html>