/**
 * Created by jerry on 2016-07-14.
 */
$(window).load(function () {
    $(function () {
        $('#promotingInfo').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "info": true,
            "autoWidth": false
        });
    });
});
