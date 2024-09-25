// SPDX-License-Identifier: MIT
pragma solidity >=0.4.22 <0.9.0;

import "./Padrao.sol";

contract Criador {

    address public endereco;

    function criaPadrao() public {
        Padrao padrao = new Padrao();
        endereco = address(padrao);
    }

}