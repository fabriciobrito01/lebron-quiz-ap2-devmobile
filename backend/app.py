from fastapi import FastAPI, Depends, HTTPException
from sqlalchemy.orm import Session

from database import SessionLocal, engine
from models import Resultado
from schemas import ResultadoCreate, ResultadoResponse

import models

models.Base.metadata.create_all(bind=engine)

app = FastAPI(
    title="LeBron Legacy Quiz API",
    version="1.0.0"
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


# CREATE
@app.post("/resultados", response_model=ResultadoResponse)
def criar_resultado(
    resultado: ResultadoCreate,
    db: Session = Depends(get_db)
):
    novo_resultado = Resultado(
        nome=resultado.nome,
        acertos=resultado.acertos,
        nivel=resultado.nivel,
        data=resultado.data
    )

    db.add(novo_resultado)
    db.commit()
    db.refresh(novo_resultado)

    return novo_resultado


# READ ALL
@app.get("/resultados", response_model=list[ResultadoResponse])
def listar_resultados(db: Session = Depends(get_db)):
    return db.query(Resultado).all()


# READ ONE
@app.get("/resultados/{resultado_id}",
         response_model=ResultadoResponse)
def buscar_resultado(
    resultado_id: int,
    db: Session = Depends(get_db)
):
    resultado = db.query(Resultado).filter(
        Resultado.id == resultado_id
    ).first()

    if not resultado:
        raise HTTPException(
            status_code=404,
            detail="Resultado não encontrado"
        )

    return resultado


# UPDATE
@app.put("/resultados/{resultado_id}",
         response_model=ResultadoResponse)
def atualizar_resultado(
    resultado_id: int,
    dados: ResultadoCreate,
    db: Session = Depends(get_db)
):

    resultado = db.query(Resultado).filter(
        Resultado.id == resultado_id
    ).first()

    if not resultado:
        raise HTTPException(
            status_code=404,
            detail="Resultado não encontrado"
        )

    resultado.nome = dados.nome
    resultado.acertos = dados.acertos
    resultado.nivel = dados.nivel
    resultado.data = dados.data

    db.commit()
    db.refresh(resultado)

    return resultado


# DELETE
@app.delete("/resultados/{resultado_id}")
def deletar_resultado(
    resultado_id: int,
    db: Session = Depends(get_db)
):

    resultado = db.query(Resultado).filter(
        Resultado.id == resultado_id
    ).first()

    if not resultado:
        raise HTTPException(
            status_code=404,
            detail="Resultado não encontrado"
        )

    db.delete(resultado)
    db.commit()

    return {
        "message": "Resultado removido com sucesso"
    }