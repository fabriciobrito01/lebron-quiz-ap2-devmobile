from fastapi import FastAPI, Depends
from sqlalchemy.orm import Session

from database import SessionLocal, engine
from models import Resultado
from schemas import ResultadoCreate

import models

models.Base.metadata.create_all(bind=engine)

app = FastAPI(
    title="LeBron Legacy Quiz API"
)

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


@app.get("/")
def home():
    return {"message": "API funcionando"}