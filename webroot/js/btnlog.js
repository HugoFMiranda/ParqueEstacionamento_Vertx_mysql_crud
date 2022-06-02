//** Login/Registar */
/**
 * It changes the display of the login and register forms.
 * </code>
 * @param x - 0 or 1
 */
function loginbtn(x) {
    let y;
    var btnlog = document.getElementById("botao_login");
    var btnreg = document.getElementById("botao_registo");
    var reg = document.getElementById("registar");
    var log = document.getElementById("login");
    if (x == 0) {
        y = "registar";
        log.style.display = "none";
        reg.style.display = "inline";
        btnlog.style.backgroundColor = "#e8e9ec";
        btnlog.style.color = "#777";
        btnreg.style.backgroundColor = "#2d3b55";
        btnreg.style.color = "#fff";
    } else {
        y = "login";
        log.style.display = "inline";
        reg.style.display = "none";
        btnreg.style.backgroundColor = "#e8e9ec";
        btnreg.style.color = "#777";
        btnlog.style.backgroundColor = "#2d3b55";
        btnlog.style.color = "#fff";
    }
    console.log(y);
}