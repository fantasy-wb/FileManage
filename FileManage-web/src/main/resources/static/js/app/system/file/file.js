var parentUrl = currentNav.replace(/&nbsp;&nbsp;/,"") + "/home";//egUrl:  1/home/



$(function () {

    var $fileTableForm = $(".file-table-form");
    var settings = {
        url: ctx + "file/selectAll",
        pageSize: 10,
        striped: false,

        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                fileName: $fileTableForm.find("input[name='fileName']").val().trim(),
                parentUrl: parentUrl.replace("&nbsp;&nbsp;",""),
            };
        },
        columns: [{
            checkbox: true,
        },{
            field: 'fileId',
            visible: false
        }, {
            field: 'fileType',
            width: '2%',
            formatter: function (value, row, index) {
                return '<img class="file_type" src="/FileManage/img/fileIcon/fileicon_'+value+'.png" width="20" height="20">';
            }
        }, {

            field: 'fileName',
            title: '文件名',
            width: '53%',
        }, {

        }, {
            field: 'fileSize',
            title: '大小',
            width: '20%'

        }, {
            field: 'modifyDate',
            title: '最近修改日期',
            width: '25%'
        },{
            field: 'parentUrl',
            visible: false
        },{
            field: 'deptId',
            visible: false
        },{
            field: 'userId',
            visible: false
        },{
            field: 'fileUrl',
            visible: false
        },{
            field: 'isDirectory',
            visible: false
        }
        ]

    };
    $MB.initTable('fileTable', settings);
    resetFileNav("fileNavigation",parentUrl);
});

function search() {
    $MB.refreshTable('fileTable');
}


//双击表单事件
$("#fileTable").on("dbl-click-row.bs.table", function (e, row, $element) {

    if(row.fileType === 'dir') {//双击文件夹
        $.ajax({
            url: ctx + "file/list",
            data: {
                parentUrl: row.parentUrl + "/" + row.filename
            },
            dataType: "json",
            async: true, //默认异步
            success: function (data) {
                $("#fileTable").bootstrapTable('load', data);
                parentUrl = row.parentUrl + "/" + row.filename;
                resetFileNav("fileNavigation",parentUrl);
            }
        });
    }else {

        $.ajax({
            url: ctx + "file/downloadFile",
            data: {
                field: row.field
            },
            dataType: "json",
            async: true, //默认异步
            success: function (data) {
                $("#fileTable").bootstrapTable('load', data);
                parentUrl = row.parentUrl + "/" + row.filename;
                resetFileNav("fileNavigation", parentUrl);
            }
        });
    }
})

function refresh() {
    $(".file-table-form")[0].reset();
    $MB.refreshTable('fileTable');
}

function returnParentUrl() {
    loadTargetData(getParentUrl(parentUrl));
}


function loadTargetData(targetUrl) {//向后端发送数据请求

    $.ajax({
        url: ctx + "file/list",
        data: {
            parentUrl: row.parentUrl + "/" + row.filename
        },
        dataType: "json",
        async: true, //默认异步
        success: function (data) {
            $("#fileTable").bootstrapTable('load', data);
            parentUrl = targetUrl;
            resetFileNav("fileNavigation",targetUrl);
        }
    });

}


function deleteFiles() {
    var selected = $("#fileTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除文件或文件夹！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].fileId;
        if (i !== (selected_length - 1)) ids += ",";
        if (fileName === selected[i].filename) contain = true;
    }
    if (contain) {
        $MB.n_warning('勾选用户中包含当前登录用户，无法删除！');
        return;
    }

    $MB.confirm({
        text: "确定删除选中文件？",
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
function resetFileNav(navId,currentUrl){

    var navStr = "<li class=" + '"returnParent"' + " onclick=" + '"returnParentUrl()"' + "><a href='" + "#" + "'>&nbsp;&nbsp;返回上一级</a></li>";

    if((currentUrl != "") && (currentUrl != null)) {//处理路径动态生成面包屑   eg：home/dir1/dir2
        var dirList = currentUrl.split('/');
        for(var i = 1; i < dirList.length - 1;i++) {
            var parentUrl = "";
            for(var m = 1; m < i - 1; m++){
                parentUrl += dirList[i] + "/";
            }
            parentUrl += dirList[i];
            navStr += "<li><a href=\"#\" onclick=\"loadTargetData("+parentUrl+")\">"+dirList[i]+"</a></li>";
        }
        navStr += "<li class=\"active\">" + dirList[dirList.length-1] + "</li>";
    }

    document.getElementById(navId).innerHTML = navStr;
}


function getParentUrl(currentUrl) {
    if(currentUrl.isNullOrUndefined||(!currentUrl.contains('/'))){
        return currentUrl;
    }
    return currentUrl.substring(0,currentUrl.lastIndexOf("/"));
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