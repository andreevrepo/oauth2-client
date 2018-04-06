$('#editModal').on('show.bs.modal',function (event) {

    var button = $(event.relatedTarget);
    var btn_id = button.attr('id');
    var tr = document.getElementById('tr_' + btn_id.charAt(4));
    var modal = $(this);

    modal.find('.modal-title').text('Edit user ' + tr.cells[2].innerText + ' ' + tr.cells[3].innerText);
    modal.find('.modal-body input[id=user-id]').val(tr.cells[0].innerText);
    modal.find('.modal-body input[id=user-name]').val(tr.cells[2].innerText);
    modal.find('.modal-body input[id=user-email]').val(tr.cells[3].innerText);
    modal.find('.modal-body input[id=user-password]').val(tr.cells[4].innerText);
});

$('#modal_submit').click(function () {
    document.getElementById('user-id').disabled = false;
});