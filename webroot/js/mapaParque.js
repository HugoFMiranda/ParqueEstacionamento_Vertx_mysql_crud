const pisom1 = document.getElementById("piso_m1").getElementsByTagName("div");
const piso0 = document.getElementById("piso_0").getElementsByTagName("div");
const piso1 = document.getElementById("piso_1").getElementsByTagName("div");
const npiso = document.querySelector("#npiso");
//const para = document.getElementById("piso_m1").getElementsByClassName("paralelograma");
const para = document.getElementById("piso_m1").getElementsByTagName("div");

function mpiso() {
    if (npiso.selectedIndex == 1) {
        for (let index = 0; index < para.length; index++) {
            pisom1[index].style.display = "block";
        }
        for (let index = 0; index < para.length; index++) {
            piso0[index].style.display = "block";
        }
        for (let index = 0; index < para.length; index++) {
            piso1[index].style.display = "block";
        }
        document.getElementById("piso_m1").style.position = "relative";
        document.getElementById("piso_1").style.position = "relative";
        document.getElementById("piso_0").style.position = "relative";
    } else if (npiso.selectedIndex == 2) {
        for (let index = 0; index < para.length; index++) {
            pisom1[index].style.display = "block";
        }
        for (let index = 0; index < para.length; index++) {
            piso0[index].style.display = "none";
        }
        for (let index = 0; index < para.length; index++) {
            piso1[index].style.display = "none";
        }
        document.getElementById("piso_m1").style.position = "relative";
        document.getElementById("piso_1").style.position = "absolute";
        document.getElementById("piso_0").style.position = "absolute";
    } else if (npiso.selectedIndex == 3) {
        for (let index = 0; index < para.length; index++) {
            pisom1[index].style.display = "none";
        }
        for (let index = 0; index < para.length; index++) {
            piso0[index].style.display = "block";
        }
        for (let index = 0; index < para.length; index++) {
            piso1[index].style.display = "none";
        }
        document.getElementById("piso_0").style.position = "relative";
        document.getElementById("piso_m1").style.position = "absolute";
        document.getElementById("piso_1").style.position = "absolute";
    } else if (npiso.selectedIndex == 4) {
        for (let index = 0; index < para.length; index++) {
            pisom1[index].style.display = "none";
        }
        for (let index = 0; index < para.length; index++) {
            piso0[index].style.display = "none";
        }
        for (let index = 0; index < para.length; index++) {
            piso1[index].style.display = "block";
        }
        document.getElementById("piso_m1").style.position = "absolute";
        document.getElementById("piso_0").style.position = "absolute";
        document.getElementById("piso_1").style.position = "relative";
    }
}