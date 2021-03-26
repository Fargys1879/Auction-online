jQuery(document).ready(function() {
        $('#deleteProductForm').submit(function() {
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
        $('#updateProductForm').submit(function() {
            var form = $( this ),
                url = form.attr('action'),
                productName = form.find('input[name="productName"]').val(),
                description = form.find('textarea[name="description"]').val(),
                startPrice = form.find('input[name="startPrice"]').val(),
                rateStep = form.find('input[name="rateStep"]').val(),
                dat = JSON.stringify({
                "productName" : productName,
                "description" : description,
                "startPrice" : startPrice,
                "rateStep" : rateStep,
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