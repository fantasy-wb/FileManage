$(function () {

    var $fileTableForm = $(".file-table-form");
    indexUrl = currentNav.replace(/&nbsp;&nbsp;/, "") + "/home";//egUrl:  1/home/
    var settings = {
        url: ctx + "file/list",
        pageSize: 10,
        striped: false,

        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                fileName: $fileTableForm.find("input[name='fileName']").val().trim(),
                parentUrl: indexUrl.replace("&nbsp;&nbsp;", ""),
            };
        },
        columns: [{
            checkbox: true,
        }, {
            field: 'fileId',
            visible: false
        }, {
            field: 'fileType',
            width: '2%',
            formatter: function (value, row, index) {
                return '<img class="file_type" src="/FileManage/img/fileIcon/fileicon_' + value + '.png" width="20" height="20">';
            }
        }, {

            field: 'fileName',
            title: '文件名',
            width: '53%',
        }, {}, {
            field: 'fileSize',
            title: '大小',
            width: '20%'

        }, {
            field: 'modifyDate',
            title: '最近修改日期',
            width: '25%'
        }, {
            field: 'parentUrl',
            visible: false
        }, {
            field: 'deptId',
            visible: false
        }, {
            field: 'userId',
            visible: false
        }, {
            field: 'fileUrl',
            visible: false
        }
        ]

    };
    $MB.initTable('fileTable', settings);
    resetFileNav("fileNavigation", indexUrl);
});

function search() {
    $MB.refreshTable('fileTable');
}

//双击表单事件
$("#fileTable").on("dbl-click-row.bs.table", function (e, row, $element) {

    if (row.fileType === 'dir') {//双击文件夹

        $.ajax({

            url: ctx + "file/list",
            data: {
                parentUrl: row.parentUrl + "/" + row.fileName
            },
            dataType: "json",
            async: true, //默认异步
            success: function (data) {
                $("#fileTable").bootstrapTable('load', data);
                indexUrl = row.parentUrl + "/" + row.fileName;
                resetFileNav("fileNavigation", indexUrl);
            }
        });
    } else {

        var form=$("<form>");

        //设置表单状态为不显示
        form.attr("style","display:none");

        //method属性设置请求类型为get
        form.attr("method","get");

        //action属性设置请求路径,(如有需要,可直接在路径后面跟参数)
        //例如:htpp://127.0.0.1/test?id=123
        form.attr("action",row.fileUrl);

        //将表单放置在页面(body)中
        $("body").append(form);

        //表单提交
        form.submit();

        // $.ajax({
        //     url: ctx + "file/downloadFile",
        //     data: {
        //         field: row.fileUrl
        //     },
        //     dataType: "json",
        //     async: true, //默认异步
        //     success: function (data) {
        //         $MB.n_success(data.msg);
        //     }
        // });
    }

})

function refresh() {
    $(".file-table-form")[0].reset();
    $MB.refreshTable('fileTable');
}

function returnParentUrl() {

    var targetUrl = getParentUrl(indexUrl);

    $.ajax({
        url: ctx + "file/list",
        data: {
            parentUrl: targetUrl
        },
        dataType: "json",
        async: true, //默认异步
        success: function (data) {
            $("#fileTable").bootstrapTable('load', data);
            indexUrl = targetUrl;
            resetFileNav("fileNavigation", targetUrl);
        }
    });

}


function loadTargetData(event,$this) {//向后端发送数据请求

    var targetUrl = $this.getAttribute("title");

    $.ajax({
        url: ctx + "file/list",
        data: {
            parentUrl: targetUrl
        },
        dataType: "json",
        async: true, //默认异步
        success: function (data) {
            $("#fileTable").bootstrapTable('load', data);
            indexUrl = targetUrl;
            resetFileNav("fileNavigation", targetUrl);
        }
    });

}


function deleteFiles() {
    var selected = $("#fileTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    var alertTxt = "";
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除文件/文件夹！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].fileId;
        if (i !== (selected_length - 1)) ids += ",";
        if ("dir" === selected[i].fileType) contain = true;
    }

    if (contain) {
        alertTxt = "确定删除选中的文件/文件夹？"
    } else {
        alertTxt = "确定删除选中文件？"
    }
    $MB.confirm({
        text: alertTxt,
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'file/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

//在ID为navId的标签中构建文件路径面包屑
function resetFileNav(navId, currentUrl) {

    var navStr = "<li class=" + '"returnParent"' + " onclick=" + '"returnParentUrl()"' + "><a href='" + "#" + "'>&nbsp;&nbsp;返回上一级</a></li>";

    if ((currentUrl != "") && (currentUrl != null)) {//处理路径动态生成面包屑   eg：(id & deptName)/home/dir1/dir2
        var dirList = currentUrl.split('/');
        for (var i = 1; i < dirList.length - 1; i++) {
            var targetUrl = currentUrl.substring(0, currentUrl.indexOf(dirList[i]) + dirList[i].length).trim();
            var reg = new RegExp("/","g")
            //targetUrl = targetUrl.replace(reg,"&#47;");
            navStr += "<li><a href=\"#\" onclick=\"loadTargetData(event,this)\" title="+targetUrl+">" + dirList[i] + "</a></li>";
        }
        navStr += "<li class=\"active\">" + dirList[dirList.length - 1] + "</li>";
    }

    document.getElementById(navId).innerHTML = navStr;
}

function getParentUrl(currentUrl) {
    return currentUrl.substring(0, currentUrl.lastIndexOf("/"));
}

function exportfileExcel() {
    $.post(ctx + "file/excel", $(".file-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportfileCsv() {
    $.post(ctx + "file/csv", $(".file-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}