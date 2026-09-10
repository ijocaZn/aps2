# APS2 - APIs REST

Aplicação Spring Boot com APIs REST para o campeonato de futebol e para uma loja virtual.
Os dados ficam armazenados em memória e são perdidos quando a aplicação é encerrada.

## Pré-requisitos

- Java 25
- Windows: use `mvnw.cmd`
- Linux/macOS: use `./mvnw`

## Executar

Na raiz do projeto:

```bash
# Windows PowerShell
./mvnw.cmd spring-boot:run

# Linux/macOS
./mvnw spring-boot:run
```

A aplicação ficará disponível em `http://localhost:8080`.

Para executar os testes:

```bash
# Windows PowerShell
./mvnw.cmd test

# Linux/macOS
./mvnw test
```

## Exercício 1 - Campeonato

Os exemplos abaixo usam `curl`. Também podem ser reproduzidos no Postman ou em um arquivo `.http`.

### Criar campeonato

```bash
curl -X POST http://localhost:8080/campeonatos ^
  -H "Content-Type: application/json" ^
  -d "{\"nome\":\"Brasileirao\",\"ano\":2026}"
```

Guarde o `id` retornado como `CAMPEONATO_ID`.

### Criar times

```bash
curl -X POST http://localhost:8080/times ^
  -H "Content-Type: application/json" ^
  -d "{\"nome\":\"Corinthians\",\"cidade\":\"Sao Paulo\"}"

curl -X POST http://localhost:8080/times ^
  -H "Content-Type: application/json" ^
  -d "{\"nome\":\"Palmeiras\",\"cidade\":\"Sao Paulo\"}"
```

Guarde os IDs retornados como `MANDANTE_ID` e `VISITANTE_ID`.

### Criar jogador e adicionar ao time

```bash
curl -X POST http://localhost:8080/jogadores ^
  -H "Content-Type: application/json" ^
  -d "{\"nome\":\"Jogador 1\",\"numero\":10,\"posicao\":\"Meia\",\"idade\":20}"
```

Guarde o ID retornado como `JOGADOR_ID` e execute:

```bash
curl -X POST http://localhost:8080/times/MANDANTE_ID/jogadores/JOGADOR_ID
```

### Registrar partida, placar e cartão

```bash
curl -X POST http://localhost:8080/partidas ^
  -H "Content-Type: application/json" ^
  -d "{\"data\":\"2026-09-10\",\"idTimeMandante\":\"MANDANTE_ID\",\"idTimeVisitante\":\"VISITANTE_ID\",\"idCampeonato\":\"CAMPEONATO_ID\"}"
```

Guarde o ID retornado como `PARTIDA_ID`.

```bash
curl -X PATCH http://localhost:8080/partidas/PARTIDA_ID/placar ^
  -H "Content-Type: application/json" ^
  -d "{\"golMandante\":2,\"golVisitante\":1}"

curl -X POST http://localhost:8080/partidas/PARTIDA_ID/cartoes ^
  -H "Content-Type: application/json" ^
  -d "{\"idJogador\":\"JOGADOR_ID\",\"minuto\":35,\"tipo\":\"AMARELO\"}"
```

### Consultar jogadores, times e classificação

```bash
curl http://localhost:8080/jogadores
curl http://localhost:8080/jogadores/JOGADOR_ID
curl http://localhost:8080/times
curl http://localhost:8080/times/MANDANTE_ID
curl http://localhost:8080/campeonatos/CAMPEONATO_ID/classificacao
```

## Exercício 2 - Loja virtual

### Criar categoria e produto

```bash
curl -X POST http://localhost:8080/categorias ^
  -H "Content-Type: application/json" ^
  -d "{\"codigo\":1,\"nome\":\"Eletronicos\"}"

curl -X POST http://localhost:8080/produtos ^
  -H "Content-Type: application/json" ^
  -d "{\"codigo\":100,\"nome\":\"Teclado\",\"preco\":150.0,\"codigoCategoria\":1}"
```

### Listar produtos e filtrar por categoria

```bash
curl http://localhost:8080/produtos
curl "http://localhost:8080/produtos?categoriaId=1"
```

A paginação opcional usa `page` começando em zero e `size`:

```bash
curl "http://localhost:8080/produtos?categoriaId=1&page=0&size=10"
```

### Criar cliente e consultar pedidos

O CPF pode ser enviado com ou sem pontuação, mas deve conter 11 dígitos válidos.

```bash
curl -X POST http://localhost:8080/clientes ^
  -H "Content-Type: application/json" ^
  -d "{\"nome\":\"Maria\",\"cpf\":\"123.456.789-09\",\"email\":\"maria@example.com\"}"

curl http://localhost:8080/clientes
curl http://localhost:8080/clientes/12345678909/pedidos
```

### Criar pedido, adicionar item e alterar status

```bash
curl -X POST http://localhost:8080/pedidos ^
  -H "Content-Type: application/json" ^
  -d "{\"numero\":1,\"cpfCliente\":\"12345678909\"}"

curl -X POST http://localhost:8080/pedidos/1/itens ^
  -H "Content-Type: application/json" ^
  -d "{\"codigoProduto\":100,\"quantidade\":2}"

curl -X PATCH http://localhost:8080/pedidos/1/status ^
  -H "Content-Type: application/json" ^
  -d "{\"status\":\"PAGO\"}"
```

Pedidos começam com status `ABERTO`. Depois de pagos ou cancelados, não aceitam novos itens. O preço unitário é copiado para o item no momento da inclusão e não muda se o preço do produto for alterado posteriormente.

## Endpoints principais

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/jogadores` | Cria jogador |
| GET | `/jogadores` | Lista jogadores |
| GET | `/jogadores/{id}` | Busca jogador |
| POST | `/times` | Cria time |
| GET | `/times` | Lista times |
| GET | `/times/{id}` | Busca time com jogadores |
| POST | `/times/{idTime}/jogadores/{idJogador}` | Adiciona jogador ao time |
| POST | `/partidas` | Registra partida |
| PATCH | `/partidas/{id}/placar` | Atualiza placar |
| POST | `/partidas/{id}/cartoes` | Registra cartão |
| POST | `/campeonatos` | Cria campeonato |
| GET | `/campeonatos/{id}/classificacao` | Consulta classificação |
| POST | `/categorias` | Cria categoria |
| POST | `/produtos` | Cria produto |
| GET | `/produtos` | Lista produtos |
| POST | `/clientes` | Cria cliente |
| GET | `/clientes` | Lista clientes |
| GET | `/clientes/{cpf}/pedidos` | Lista pedidos do cliente |
| POST | `/pedidos` | Cria pedido |
| POST | `/pedidos/{id}/itens` | Adiciona item ao pedido |
| PATCH | `/pedidos/{id}/status` | Altera status do pedido |
