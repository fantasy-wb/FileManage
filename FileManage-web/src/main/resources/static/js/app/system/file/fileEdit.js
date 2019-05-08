function updateFile() {
    var selected = $("#fileTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的用户！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个用户！');
        return;
    }
    var fileId = selected[0].fileId;
    $.post(ctx + "file/getfile", {"fileId": fileId}, function (r) {
        if (r.code === 0) {
            var $form = $('#file-add');
            var $deptTree = $('#deptTree');
            $form.modal();
            var file = r.msg;
            $form.find(".file_password").hide();
            $("#file-add-modal-title").html('修改用户');
            $form.find("input[name='filename']").val(file.filename).attr("readonly", true);
            $form.find("input[name='oldfilename']").val(file.filename);
            $form.find("input[name='fileId']").val(file.fileId);
            $form.find("input[name='email']").val(file.email);
            $form.find("input[name='mobile']").val(file.mobile);
            var roleArr = [];
            for (var i = 0; i < file.roleIds.length; i++) {
                roleArr.push(file.roleIds[i]);
            }
            $form.find("select[name='rolesSelect']").multipleSelect('setSelects', roleArr);
            $form.find("input[name='roles']").val($form.find("select[name='rolesSelect']").val());
            var $status = $form.find("input[name='status']");
            if (file.status === '1') {
                $status.prop("checked", true);
                $status.parent().next().html('可用');
            } else {
                $status.prop("checked", false);
                $status.parent().next().html('禁用');
            }
            $("input:radio[value='" + file.ssex + "']").prop("checked", true);
            $deptTree.jstree().open_all();
            $deptTree.jstree('select_node', file.deptId, true);
            $("#file-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

function addFile() {
    
}