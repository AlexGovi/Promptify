# 📘 Promptify – Smart AI-Powered Text Assistant

**Promptify** is an intelligent text analysis tool built with **Spring Boot** and powered by **Google's Gemini API**. It helps users perform various natural language processing tasks such as summarization, translation, keyword extraction, tone/sentiment analysis, paraphrasing, and more — all through a simple REST API.

---

## 🚀 Features

Promptify supports the following operations via a single smart endpoint:

| Operation | Description |
|----------|-------------|
| `summarize` | Generate a short summary of the input text |
| `summarize_bullets` | Summarize content using bullet points |
| `suggest` | Suggest related topics and readings |
| `extract_keywords` | Extract key terms and concepts |
| `translate` | Translate content to English |
| `eli5` | Explain like I’m five (simple explanation) |
| `find_similar` | Recommend related research topics |
| `generate questions` | Create research-based questions |
| `paraphrase` | Reword the content while keeping its meaning |
| `spell_check` | Identify and correct spelling mistakes |
| `grammar_check` | Detect and fix grammar errors |
| `tone_analysis` | Analyze the tone of the text |
| `sentiment_analysis` | Determine sentiment (positive, negative, neutral) |
| `convert_to_formal` | Make the tone more formal |
| `convert_to_casual` | Make the tone more casual |
| `detect_language` | Identify the language used in the text |

---

## 🛠️ Tech Stack

- **Java** + **Spring Boot**
- **Gemini API (Google)** – for all AI-powered responses
- **Postman** – for manual API testing

---

## 📥 API Usage

### Endpoint

```
POST /api/ask
```

### Request Body

```json
{
  "operation": "summarize",
  "content": "Artificial Intelligence is a branch of computer science..."
}
```

### Response Example

```json
{
  "answer": "AI is a field of computer science focused on building intelligent systems..."
}
```

---

## ✅ Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/Promptify.git
cd scholarmate
```

### 2. Add Gemini API Key

Create an `application.properties` file (or use `application.yml`) and include:

```
gemini.api.key=your_api_key_here
```

### 3. Run the Project

```bash
./mvnw spring-boot:run
```

---

## 📌 Future Enhancements

- Add user authentication with JWT
- Store query history in a database
- Frontend UI using React or Angular

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

---

## 🙋‍♂️ Author

Made with ❤️ by **[Your Name]**  
Feel free to connect: [LinkedIn](https://www.linkedin.com/in/govind-shewale-53b06a202/)

