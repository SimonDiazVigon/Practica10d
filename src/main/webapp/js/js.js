var anterior = 0;

function funcion(){
	var altura = window.innerHeight-119-document.getElementById("info").offsetWidth*0.175;
	document.getElementById("selector_sondas").style.height = altura +"px";
	document.getElementById("cont_info_sonda").style.height = altura +"px";
	
	//document.getElementById("info").innerHTML = altura +"px";
}

function getFrase() {
	var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4) {
			
			if (this.status == 200){
				document.getElementById("frase").innerHTML = this.responseText;
			}
		}
	};
	xhttp.open("GET", "frase", true);
	xhttp.send();

}
function traducir(idioma) {
	document.getElementById("frase").innerHTML = document.getElementsByName("idioma");

	/var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4) {
			
			if (this.status == 200){
				document.getElementById("frase").innerHTML = this.responseText;
			}
		}
	};
	xhttp.open("GET", "frase", true);
	xhttp.send();/

}
function getFrase() {
	var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4) {
			
			if (this.status == 200){
				document.getElementById("frase").innerHTML = this.responseText;
			}
		}
	};
	xhttp.open("GET", "frase", true);
	xhttp.send();

}
function initServer(){
	
}