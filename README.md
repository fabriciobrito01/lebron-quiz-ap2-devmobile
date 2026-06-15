# 🏀 LeBron Quiz - Avaliação Parcial 2 (AP2)

Este aplicativo foi desenvolvido para a disciplina de **Desenvolvimento Mobile**. Trata-se de um sistema interativo de quiz sobre a carreira do atleta LeBron James, integrado a uma API REST para persistência de dados em um banco de dados relacional.

---

## 🛠️ Requisitos Técnicos Implementados

### 1. Estrutura e Navegação
- **Múltiplas Telas:** O app conta com 5 telas principais (Splash/Home, Cadastro, Quiz, Resultado e Histórico).
- **Fragments:** Implementação de Fragment funcional para a lógica de perguntas do quiz.
- **Intents Explícitas:** Navegação estruturada entre as Activities do sistema.
- **Intent Implícita:** Funcionalidade de compartilhamento de pontuação (ACTION_SEND) e link externo para perfil do atleta.

### 2. Interface Gráfica (UI/UX)
Interface desenvolvida seguindo padrões de usabilidade com os seguintes componentes:
- `TextView` (Textos e perguntas)
- `EditText` (Entrada de nome do usuário)
- `Button` (Ações do sistema)
- `ImageView` (Imagens dinâmicas)
- `RadioButton` / `RadioGroup` (Seleção de respostas)
- `ProgressBar` (Indicador de progresso do Quiz)
- `RecyclerView` (Listagem de histórico/ranking via API)

### 3. Integração com API REST e Banco de Dados
- **Backend:** Consumo de API REST para trafegar dados em formato JSON.
- **Persistência:** Armazenamento das pontuações em Banco de Dados Relacional via API.
- **Retrofit:** Utilizado para chamadas assíncronas de rede.
- **Documentação:** API documentada via Swagger/OpenAPI.

---

## 🏗️ Arquitetura e Organização
O projeto utiliza o padrão **MVVM (Model-View-ViewModel)**, garantindo:
- Separação de responsabilidades.
- Código limpo e legível.
- Facilidade de manutenção e escalabilidade.

---
