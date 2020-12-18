function carregaInfo(){
    //primeira coisa: usuario ta logado?
    var objUser = localStorage.getItem("dashcardUser");
    if (!objUser){ //o ! siginifica se este objeto não existir no local Storage, significa que não esta logado, então direciona para o index
        window.location = "index.html";
    }

    var user = JSON.parse(objUser); //reconverte de string para objeto para facilitar


    // eu gero "dinamicamente" conteúdo HTML nos textos das DIV para que eu consiga
    // mostrar os dados que eu quiser
    document.getElementById("fotoUser").innerHTML = `<img src="${user.linkFoto}" width="100%">`;
    document.getElementById("infoUser").innerHTML = `<h3>${user.nome}</h3>
                                                     <strong>RACF</strong> ${user.racf} <br>
                                                     <strong>EMAIL</strong> ${user.email} <br>`;


    /* e se eu quiser recuperar os agentes?
    passo 1 - fazer um fetch (Sem cabeçalho) para o backend
    passo 2 - tratar o resultado gerando uma tabela para exibir 1 agente financeiro por linha*/

    //passo 1
    fetch("http://localhost:8088/agentes")
        .then(resposta => resposta.json())  // se houver resposta, extrai o json dela
        .then(lista => preencheLista(lista));   // se conseguiu extrair a lista, chama a função com essa lista

    /* se eu fosse traduzir as instruções acima para modo sequencial 
    resposta = fetch("http://localhost:8088/agentes");
    lista = resposta.json();
    preencheLista(lista) ;  
    */
}

function preencheLista(lista){
   
    var linha = "linhaPar";
    var strSelect = `<select id="selectAgente" class="form-select">
                        <option value="-1">-- SELECIONE SEU AGENTE --</option>`
    var strLista = `<div class = "row">
                       <div class = "col-8 text-center"> <strong> Agente Financeiro</strong> </div>
                       <div class = "col-4 text-center"> <strong>Volume</strong> </div>
                    </div>`;
    for (i=0; i<lista.length; i++){
        var agente = lista[i];
        if (i%2==0){
            linha = "linhaPar";
        }
        else{
            linha = "linhaImpar";
        }
        // `<${}>` substitui o valor de uma variavel
        strLista = strLista + `<div class="row ${linha}">
                                <div class="col-8">${agente.nome}</div>
                                <div class="col-4 text-center">${agente.volume}</div>
                              </div>`;
        strSelect = strSelect +`<option value="${agente.id}">${agente.nome}</option>`; 
    }
    document.getElementById("divAgente").innerHTML = strSelect;
    document.getElementById("tabelaAgentes").innerHTML = strLista;
}

function seleciona(){
    var idAgente = document.getElementById("selectAgente").value;
    console.log("Agente selecionado = "+idAgente);
    if (idAgente == -1){
        alert("Por favor, selecione um agente financeiro válido");
        return;
    }
    window.location = "dashboard.html?id="+idAgente;

}

function logout(){
    localStorage.removeItem("dashcardUser");
    window.location = "index.html";
}