function carregaDash(){
    var strUser = localStorage.getItem("dashcardUser");
    if (!strUser){
        window.location = "index.html";
    }

    //usuario esta conectado.. preciso consultar o relatório consolidado no back end
    var strId = window.location.search;
    console.log(strId);

    var idAgente = strId.substr(4);
    console.log("ID recuperado: "+idAgente);

    fetch("http://localhost:8088/totaisconsolidados?id="+idAgente)
    .then(res => res.json())
    .then(lista => preencheDash(lista));
}

function preencheDash(lista){
 console.log(lista);
 var nomeAgente;
 var volume;
 var sucessoTransacao;
 var falhaTransacao;
 var fraudeTransacao;

 for (i=0; i<lista.length; i++){
     var ag = lista[i];
     nomeAgente = ag.nomeAgente;
     volume = ag.volume;
     if(ag.status == 0){
         sucessoTransacao = ag.numeroOP;
     }
     else if(ag.status == 1){
        falhaTransacao = ag.numeroOP;
    }  
    else{
        fraudeTransacao = ag.numeroOP;
    }
 }

 document.getElementById("nomeAgente").innerHTML = "<h4>"+nomeAgente+" / Volume: "+volume+"</h4>";
 document.getElementById("sucessoTransacao").innerHTML = "<h4>"+sucessoTransacao+"</h4>";
 document.getElementById("falhaTransacao").innerHTML = "<h4>"+falhaTransacao+"</h4>";
 document.getElementById("fraudeTransacao").innerHTML = "<h4>"+fraudeTransacao+"</h4>";

}

function logout(){
    localStorage.removeItem("dashcardUser");
    window.location = "index.html";
}
