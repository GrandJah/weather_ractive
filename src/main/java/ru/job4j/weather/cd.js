fetch('http://localhost:8080/all').then(function (response) {
    console.log(response);
    const reader = response.body.getReader();

    function go() {
        reader.read().then(function (result) {
            if (!result.done) {
                var num = new TextDecoder("utf-8").decode(result.value)
                console.log(num);
                go();
            }
        })
    }

    go();
})


const go = async reader => {
    const result = await reader.read()
    if (!result.done) {
        console.log(new TextDecoder("utf-8").decode(result.value));
        go(reader);
    } else {
        console.log("empty!")
    }
    console.log("go!")
}

const f = async () => {
    const response = await fetch('http://localhost:8080/all')
    console.log("ok!")
    go(response.body.getReader());
    console.log("gogogo!")
}
