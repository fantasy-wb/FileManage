$(function () {
    var $fileTableForm = $(".file-table-form");
    var settings = {
        url: ctx + "file/test",
        pageSize: 10,
        striped: false,  //是否显示行间隔色
        //borderLeft: 0,
        //showColumns: true,    //是否显示所有的列

        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                fileName: $fileTableForm.find("input[name='fileName']").val().trim(),
            };
        },
        columns: [{
            checkbox: true,
            vAlign: 'middle'
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
            title: '文件名'

        }, {
            field: 'fileSize',
            title: '大小'
        }, {
            field: 'modifyDate',
            title: '修改日期'
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
});

function search() {
    $MB.refreshTable('fileTable');
}

$("#fileTable").on("dbl-click-row.bs.table", function (e, row, $element) {

    if(row.fileType === 'directory') {
        $.ajax({
            url: ctx + "file/list",
            data: {
                deptId: row.deptId,
                userId: row.userId,
                parentUrl: row.parentUrl + "/" + row.filename
            },
            dataType: "json",
            async: true, //默认异步
            success: function (data) {
                $("#fileTable").bootstrapTable('load', data);
            }
        });
    }else {





    }
    // var settings = {
    //     //url: ctx + "file/list",
    //     url: ctx + "file/test",
    //     pageSize: 10,
    //     queryParams: function (params,row) {
    //         alert(row.filename);
    //         return {
    //             pageSize: params.limit,
    //             pageNum: params.offset / params.limit + 1,
    //             parentUrl: row.filename.val(),
    //             filename: row.filename,
    //             ssex: $fileTableForm.find("select[name='ssex']").val(),
    //             status: $fileTableForm.find("select[name='status']").val()
    //         };
    //     },
    //     columns: [{
    //         checkbox: true
    //     },{
    //         field: 'fileId',
    //         visible: false
    //     }, {
    //         field: 'filename',
    //         title: '用户名'
    //     }, {
    //         field: 'crateTime',
    //         title: '创建时间'
    //     }
    //     ]
    //
    // };
    // $MB.reloadTable('fileTable', settings);
})

function refresh() {
    $(".file-table-form")[0].reset();
    $MB.refreshTable('fileTable');
}

function deleteFiles() {
    var selected = $("#fileTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的用户！');
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
        text: "确定删除选中用户？",
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