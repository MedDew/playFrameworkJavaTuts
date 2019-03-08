function sendDeleteRequest(deleteUrl, redirectUrl){
//    $(document).ready(function(){
        console.log("Delete Book "+deleteUrl);
         $.ajax({
                url: deleteUrl,//"/books/delete/100"
                method: "DELETE",
                success: function(){
                    window.location = redirectUrl;
                },
                error: function(jqXHR){
                    alert("XHR Error : "+jqXHR.status+" => "+ jqXHR.statusText);
                    window.location.reload();
                }
        });
//    });
}