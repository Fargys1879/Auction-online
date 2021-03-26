jQuery(document).ready(function() {
        $('#deleteUserForm').submit(function() {
            var form = $( this ),
                url = form.attr('action');


            $.ajax({
                url : url,
                type : "DELETE",
                traditional : true,
                contentType : "application/json",
                dataType : "json",

                success : function (response) {
                        alert('success');
                },
                error : function (response) {
                        obj = JSON.parse(response.responseText);
                        alert(obj.message);
                },
            });
            return false;
        });
    });
jQuery(document).ready(function() {
        $('#updateUserForm').submit(function() {
            var form = $( this ),
                url = form.attr('action'),
                name = form.find('input[name="name"]').val(),
                adress = form.find('input[name="adress"]').val(),
                login = form.find('input[name="login"]').val(),

                dat = JSON.stringify({
                "name" : name,
                "adress" : adress,
                "login" : login,

                });

            $.ajax({
                url : url,
                type : "PUT",
                traditional : true,
                contentType : "application/json",
                dataType : "json",
                data : dat,
                success : function (response) {
                        alert('success');
                },
                error : function (response) {
                        obj = JSON.parse(response.responseText);
                        alert(obj.message);
                },
            });
            return false;
        });
    });