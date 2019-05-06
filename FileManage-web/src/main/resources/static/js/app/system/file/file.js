$(function () {
    var $fileTableForm = $(".user-table-form");
    var settings = {
        url: ctx + "user/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                username: $fileTableForm.find("input[name='username']").val().trim(),
                ssex: $fileTableForm.find("select[name='ssex']").val(),
                status: $fileTableForm.find("select[name='status']").val()
            };
        },
        columns: [{
            checkbox: true
        },{
            field: 'userId',
            visible: false
        }, {
            field: 'username',
            title: '用户名'
        }, {
            field: 'crateTime',
            title: '创建时间'
        }
        ]

    };
    $MB.initTable('userTable', settings);
});

function search() {
    $MB.refreshTable('userTable');
}

$("#userTable").on("dbl-click-row.bs.table", function (e, row, $element) {



    $.ajax({
        url: ctx + "user/list",
        //  url: ctx + "/test",
        data: {
            parentUrl: row.parentUrl + "\\" + row.username
        },
        dataType: "json",
        async: true, //默认异步
        success:function(data){
            $("#userTable").bootstrapTable('load', data);
        }});
    // var settings = {
    //     //url: ctx + "user/list",
    //     url: ctx + "file/test",
    //     pageSize: 10,
    //     queryParams: function (params,row) {
    //         alert(row.username);
    //         return {
    //             pageSize: params.limit,
    //             pageNum: params.offset / params.limit + 1,
    //             parentUrl: row.username.val(),
    //             username: row.username,
    //             ssex: $fileTableForm.find("select[name='ssex']").val(),
    //             status: $fileTableForm.find("select[name='status']").val()
    //         };
    //     },
    //     columns: [{
    //         checkbox: true
    //     },{
    //         field: 'userId',
    //         visible: false
    //     }, {
    //         field: 'username',
    //         title: '用户名'
    //     }, {
    //         field: 'crateTime',
    //         title: '创建时间'
    //     }
    //     ]
    //
    // };
    // $MB.reloadTable('userTable', settings);
})

function refresh() {
    $(".user-table-form")[0].reset();
    $MB.refreshTable('userTable');
}

function deleteFiles() {
    var selected = $("#userTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的用户！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].userId;
        if (i !== (selected_length - 1)) ids += ",";
        if (userName === selected[i].username) contain = true;
    }
    if (contain) {
        $MB.n_warning('勾选用户中包含当前登录用户，无法删除！');
        return;
    }

    $MB.confirm({
        text: "确定删除选中用户？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'user/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportUserExcel() {
    $.post(ctx + "user/excel", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "user/csv", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}