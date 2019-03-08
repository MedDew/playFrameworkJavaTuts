function sendPutRequest(formId, redirectUrl){
         var form = $('#'+formId)
         console.log("Update Book");
         console.log("Form action : "+form.attr("action"));
         $.ajax({
                url: form.attr("action"),//
                method: "PUT",
                data: form.serialize(),
                success: function(){
                    window.location = redirectUrl;
                },
                error: function(jqXHR){
                    //alert("XHR Error : "+jqXHR.status+" => "+ jqXHR.statusText);
                    window.location.reload();
                }
        });
}