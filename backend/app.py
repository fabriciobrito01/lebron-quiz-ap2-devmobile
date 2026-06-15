from fastapi import FastAPI

app = FastAPI(
    title="LeBron Legacy Quiz API",
    version="1.0.0"
)

@app.get("/")
def home():
    return {
        "message": "LeBron Legacy Quiz API funcionando"
    }