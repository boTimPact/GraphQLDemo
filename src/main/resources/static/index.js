function postClicks() {
     $.ajax({
        type: "POST",
        url: "/clicks",
        data: {
             timestamp: Date.now()
        },
        success: function (data){
//            console.log("Ajax success")
         },
        error: function (data) {
            console.error("Ajax error")
        }
    })
}

function getClicks() {
    $.ajax({
        type: "GET",
        url: "/clicks",
        success: function (data){
            $("#timestamps").val(data.toString().replaceAll(",", ", "))
         },
        error: function (data) {
            console.error("Ajax error")
        }
    })
}

async function postClicksGraphQuery(){
    const mutation = JSON.stringify({
        query: `mutation myMutation($time: String){
            saveTimestamp(time: $time){
                time
            }
        }`,
        variables: {
          time: Date.now() + ""
        }
    });

    const res = await fetch('/graphql',{
        method: 'post',
        body: mutation,
        headers: {
            'Content-Type': 'application/json',
        },
    });

    const json = await res.json()
    console.log(json);
}

async function getClicksGraphQuery(){
    const query = JSON.stringify({
        query: `query MyQuery {
            getTimestamps {
                time
            }
        }`
    });

    const res = await fetch('/graphql',{
            method: 'post',
            body: query,
            headers: {
            'Content-Type': 'application/json',
            },
       }
    );

    const json = await res.json()
    const timestamps = json.data.getTimestamps.map(t => t.time).toString().replaceAll(",", ", ")
    $("#timestamps").val(timestamps)
}