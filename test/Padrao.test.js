const Padrao = artifacts.require("Padrao");

contract("Padrao", (accounts) => {
    let padrao;

    // Teste de deployment
    before(async () => {
        padrao = await Padrao.new();
    });

    it("deve ser implantado com nome vazio inicialmente", async () => {
        const nome = await padrao.nome();
        assert.equal(nome, "", "O nome inicial deve ser vazio.");
    });

    // Teste para alterar o nome
    it("deve permitir alterar o nome", async () => {
        const novoNome = "NovoNome";
        await padrao.mudaNome(novoNome);
        const nome = await padrao.nome();
        assert.equal(nome, novoNome, "O nome não foi atualizado corretamente.");
    });

    // Teste para garantir que não aceita números
    it("não deve permitir definir um nome que contenha números", async () => {
        try {
            await padrao.mudaNome("Nome123"); // Deve falhar
            assert.fail("O contrato permitiu um nome com números.");
        } catch (error) {
            assert.include(error.message, "Nome deve conter apenas letras.", "A transação não falhou como esperado.");
        }
    });

    // Teste para garantir que o nome pode ser uma string vazia
    it("deve permitir definir um nome vazio novamente", async () => {
        await padrao.mudaNome("");
        const nome = await padrao.nome();
        assert.equal(nome, "", "O nome não deve ser vazio após atualização.");
    });
});
