# T6 - Identificação de Isomorfismo em Árvores

Este projeto é o **Trabalho Prático 6** da disciplina de **Resolução de Problemas com Grafos**. O objetivo é implementar um algoritmo capaz de ler dois grafos não direcionados e determinar se eles representam árvores estruturalmente idênticas (isomorfas), utilizando a técnica matemática de **Codificação Canônica**.

## 🎥 Vídeo Explicativo

**Link para o vídeo no YouTube:** (https://drive.google.com/drive/folders/1aIaF1_HhkJufc3IZSgojyfp1QjLiX8NX?usp=sharing)
*(Nota: O vídeo contém a explicação do isomorfismo, a lógica de encontrar os centros, o funcionamento da codificação canônica com ordenação lexicográfica e a validação estrutural).*

---

## 🛠️ Estrutura do Projeto

O projeto segue a estrutura de diretórios obrigatória:

```text
T6/
├── README.md
├── T6.md
├── dados/
│   ├── invalid-ciclo3.txt
│   ├── iso-path4-a.txt
│   ├── iso-path4-b.txt
│   ├── nao-iso-estrela5.txt
│   └── nao-iso-path5.txt
└── src/
    ├── Bag.java
    ├── Graph.java
    ├── In.java
    ├── Main.java
    ├── Stack.java
    ├── StdIn.java
    ├── StdOut.java
    └── TreeIsomorphism.java
