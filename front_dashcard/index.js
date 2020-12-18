function logar(){ //atribuir essa função no comando do botão, no arquivo index html
    var txtLogin = document.getElementById("txtLogin").value; //value deve estar em caixa baixa
    var txtSenha = document.getElementById("txtSenha").value;
    console.log("Valores digitados: " + txtLogin + " / " + txtSenha);


    /*preciso montar essa mensagem para enviar
    para o back-end através do método POST e depois preciso tratar o resultado

    passo 1: montar o corpo da mensagem
    passo 2: montar o cabeçalho como se fosse o POSTMAN
    passo 3: enviar URL com a mensagem
    passo 4: tratar*/

    // passo 1
    var msgBody = {
        email : txtLogin,
        racf : txtLogin,
        senha : txtSenha
    };

    //passo 2
    var cabecalho = {
        method : "POST",
        body : JSON.stringify(msgBody),
        headers : {
            "content-type":"application/json"
        }
    };

    //passo 3
    fetch("http://localhost:8088/login", cabecalho).then(res => tratastatus(res));

    //passo 4
    function tratastatus(res){
        if (res.status == 200){
            // extraindo o conteudo do corpo da msg pela função json()
            // simbolo "=>" significa "então".. Caso consiga extrair da função json, entao irá retornar o registrarUser
            res.json().then(user => registrarUser(user));
        }
        else if (res.status == 401){
            document.getElementById("msgErro").innerHTML = "Senha Invalida";
        }
        else if (res.status == 404){
            document.getElementById("msgErro").innerHTML = "Usuário Não Encontrado";
        }
        else {
            document.getElementById("msgErro").innerHTML = "Erro Desconhecido";
        }
    }

    function registrarUser(user){
        localStorage.setItem("dashcardUser", JSON.stringify(user));
        window.location = "agentes.html";
    }


}