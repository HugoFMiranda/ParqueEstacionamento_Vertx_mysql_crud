//**check nif */
const Rnif = document.querySelector("#nifR");
const Rerrortxtnif = document.querySelector(".errotxtnif");

/**
 * If the length of the value of the input field is not equal to 9, then display the error message.
 * Otherwise, hide the error message
 */
function checknifR() {
    if (Rnif.value.length != 9) {
        Rerrortxtnif.style.display = "block";
    } else {
        Rerrortxtnif.style.display = "none";
    }
}

//** check passwords */
const Rpswrd_1 = document.querySelector("#passwordR1");
const Rpswrd_2 = document.querySelector("#passwordR2");
const Rerrortxt = document.querySelector(".errotxtpw");
const btnR = document.querySelector(".btnR");

/**
 * If the value of the first password field is not equal to the value of the second password field,
 * then display the error message and disable the submit button. Otherwise, hide the error message and
 * enable the submit button
 */
function checkpwdR() {
    if (Rpswrd_1.value != Rpswrd_2.value) {
        Rerrortxt.style.display = "block";
        btnR.setAttribute("disabled", "");
    } else {
        Rerrortxt.style.display = "none";
        btnR.removeAttribute("disabled", "");
    }
}

//** Mostra pw */
/**
 * If the password fields are set to "password", then change them to "text", otherwise change them to
 * "password".
 */
function showpwR() {
    if (Rpswrd_1.type == "password" && Rpswrd_2.type == "password") {
        Rpswrd_1.type = "text";
        Rpswrd_2.type = "text";
    } else {
        Rpswrd_1.type = "password";
        Rpswrd_2.type = "password";
    }
}

//* registar cliente/gestor */
const Rtipo = document.querySelector("#tipoR");
const Rerrortxttipo = document.querySelector(".errotxttipo");

/**
 * If the user selects the first option in the dropdown menu, then the function will display an error
 * message. If the user selects the second option, then the function will send the data to the server.
 * If the user selects the third option, then the function will send the data to the server.
 * @returns undefined.
 */
function registoCliente() {
    if (Rtipo.selectedIndex == 0) {
        Rerrortxttipo.style.display = "block";
        return;
    } else {
        Rerrortxttipo.style.display = "none";
    }
    let form = document.getElementById("registar");
    let formdata = new FormData(form);
    if (Rtipo.selectedIndex == 1) {
        fetch("/submitclientes", {
                method: "POST",
                body: formdata,
            })
            .then((res) => {
                if (res.status === 200) {
                    //alert("Registado com sucesso!");
                    return res.json();
                } else {
                    throw Error("Erro no servidor!!");
                }
            })
            .catch((err) => console.log(err));
        return;
    }
    fetch("/submitgestores", {
            method: "POST",
            body: formdata,
        })
        .then((res) => {
            if (res.status === 200) {
                //alert("Registado com sucesso!");
                return res.json();
            } else {
                throw Error("Erro no servidor!!");
            }
        })
        .catch((err) => console.log(err));
}

//** Login */
/**
 * It takes the data from the form, sends it to the server, and if the server returns a 200 status
 * code, it redirects the user to the client page, if it returns a 201 status code, it redirects the
 * user to the manager page, and if it returns any other status code, it throws an error.
 */
function verflogin() {
    let form = document.getElementById("login");
    let formdata = new FormData(form);
    fetch("/submitlogin", {
            method: "POST",
            body: formdata,
        })
        .then((res) => {
            if (res.status === 200) {
                //alert("Login efetuado com sucesso como cliente!");
                window.location.assign("/paginaCliente.html");
                return res.json();
            } else if (res.status === 201) {
                //alert("Login efetuado com sucesso como gestor!");
                window.location.assign("/pagInicialGestor.html");
                return res.json();
            } else {
                throw Error("Erro no servidor!!");
            }
        })
        .catch((err) => console.log(err));
}