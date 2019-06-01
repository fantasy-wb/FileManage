//为表格按钮添加事件，应在表格初始化之前
window.operateEvents = {
    "click #fileActive":function (e, vlaue, row, index) {
        $.ajax({
            url: ctx + "file/fileActive",
            data: {
                fileId: row.fileId,
                fileName: row.fileName,
                parentUrl: row.parentUrl
            },
            dataType: "json",
            async: true, //默认异步
            success: function (data) {
                if(data.code == 0){
                    $MB.closeAndRestModal("file-version");
                    $MB.refreshTable('fileTable');
                    $MB.n_success(data.msg)
                }else {
                    $MB.n_warning(data.msg)
                }
            }
        });
    },
    "click #fileLoad":function (e, vlaue, row, index) {
        var form = $("<form>");

        //设置表单状态为不显示
        form.attr("style", "display:none");

        //method属性设置请求类型为get
        form.attr("method", "get");

        //action属性设置请求路径,(如有需要,可直接在路径后面跟参数)
        //例如:htpp://127.0.0.1/test?id=123
        // alert(row.fileUrl + "?fileName=" + row.fileName);
        // return;
        form.attr("action", row.fileUrl);

        //将表单放置在页面(body)中
        $("body").append(form);

        //表单提交
        form.submit();
    }
}

$(function () {
    var settings = {
        url: ctx + "file/version",
        pageSize: 10,
        striped: false,

        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
            };
        },
        columns: [{
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
        }, {
            field: 'fileSize',
            title: '大小',
            width: '20%'

        }, {
            field: 'modifyDate',
            title: '最近修改日期',
            width: '25%'
        }, {
            field: 'Button',
            title: '操作',
            events: operateEvents,//按钮注册事件
            formatter: addFunctionAlty
        },{
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

    $MB.initTable('fileVersionTable', settings);
});

$("#file-version .btn-close").click(function () {
    $MB.closeAndRestModal("file-version");
    //$("#fileVersionTable").bootstrapTable('removeAll');
});

$("#fileversion").click(function (e) {

    var getSelectRows = $("#fileTable").bootstrapTable('getSelections', function (row) {
        return row;
    });

    if ((getSelectRows.length != 1) || (getSelectRows[0].fileType === 'dir')) {
        $MB.n_warning("请选择一个要查看历史版本的文件！");
        return;
    }

    $.ajax({
        url: ctx + "file/version",
        data: {
            fileName: getSelectRows[0].fileName,
            parentUrl: getSelectRows[0].parentUrl,
        },
        dataType: "json",
        async: true, //默认异步
        success: function (data) {
            $('#fileVersionTable').bootstrapTable('load',data);
        }
    });


    $('#file-version').modal('show');
})

//为表格栏添加按钮
function addFunctionAlty(value, row, index) {
    return [
        '<button id="fileLoad" type="button" class="btn btn-primary" width="20" height="20">查看</button> &nbsp;&nbsp; ',
        '<button id="fileActive" type="button" class="btn btn-primary" width="20" height="20">启用</button>'
    ].join('');
}


