var $dirAddForm = $("#dir-add-form");


$("#file-add .btn-close").click(function () {
    closeModal();
});

function closeModal() {
    $MB.closeAndRestModal("file-add");
}

function checkFileOrDirName(fileName) {

    if(fileName.contains('/')){
        return false;
    }

    return true;

}

$("#dir-add .btn-save").click(function () {
    var name = $(this).attr("name");
    var validator = $dirAddForm.validate();
    var flag = validator.form();
    if (flag) {

        $.post(ctx + "user/test", $dirAddForm.serialize(), function (r) {
            if (r.code === 0) {
                $MB.closeAndRestModal("dir-add");
                $MB.n_success(r.msg);
                $MB.refreshTable("fileTable");
            } else $MB.n_danger(r.msg);
        });

    }
});