$("button").click(function(){
    event.preventDefault();
    $.ajax({
        method:"GET",
        //url:"https://jsonplaceholder.typicode.com/posts",
        url:"https://jsonplaceholder.typicode.com/posts/1",
        //data:{"nom": $("#nom").val(), "credencial": $("#credencial").val()},
        dataType:"json",

        success:function (data){
            console.log(data);
            /*let userId = "<p>userId: "+data[0].userId+"</p>";
            let id = "<p>id: "+data[0].id+"</p>";
            let title = "<p>title: "+data[0].title+"</p>";
            let body = "<p>body: "+data[0].body+"</p>";
*/
            let userId = "<p>userId: "+data.userId+"</p>";
            let id = "<p>id: "+data.id+"</p>";
            let title = "<p>title: "+data.title+"</p>";
            let body = "<p>body: "+data.body+"</p>";

                        //amb un after afegim tots esl elements
            $("button").after(userId,id,title,body);
            window.stop();
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);

        }
    })
});