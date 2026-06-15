from pydantic import BaseModel

class ResultadoCreate(BaseModel):
    nome: str
    acertos: int
    nivel: str
    data: str

class ResultadoResponse(ResultadoCreate):
    id: int

    class Config:
        from_attributes = True