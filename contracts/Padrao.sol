// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;
contract Padrao {
    string public nome;

    function mudaNome(string memory _nome) public{
        nome = _nome;
    }

}