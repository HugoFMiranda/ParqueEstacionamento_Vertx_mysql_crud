/**
 * It fetches the user's name from the server and displays it on the page.
 */
function loadusr() {
    fetch("/getUsr")
        .then((res) => {
            if (res.status === 200)
                return res.json();
            else
                throw Error("Erro no servidor!!");
        })
        .then((data) => {
            let usrname = data.nome;
            document.querySelector(".nome-lbl").innerHTML = usrname;
        })
        .catch((err) => console.log(err));
}

/**
 * It sends a POST request to the server, and if the server responds with a 200 status code, it
 * redirects the user to the index.html page
 */
function sairUsr() {
    fetch("/sairUsr", {
            method: "POST",
        })
        .then((res) => {
            if (res.status === 200) {
                window.location.assign("/index.html");
                return res.json();
            } else {
                throw Error("Erro no servidor!!");
            }
        })
        .catch((err) => console.log(err));
}