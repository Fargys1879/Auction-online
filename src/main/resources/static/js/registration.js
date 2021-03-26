jQuery(document).ready(function() {
        $('#registerForm').submit(function() {
            var form = $( this ),
                url = form.attr('action'),
                name = form.find('input[name="name"]').val(),
                adress = form.find('input[name="adress"]').val(),
                login = form.find('input[name="login"]').val(),
                password = form.find('input[name="password"]').val(),
                dat = JSON.stringify({
                "name" : name,
                "adress" : adress,
                "login" : login,
                "password" : password,
                });

            $.ajax({
                url : url,
                type : "POST",
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