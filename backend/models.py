from sqlalchemy import Column, Integer, String
from database import Base

class Resultado(Base):
    __tablename__ = "resultados"

    id = Column(Integer, primary_key=True, index=True)
    nome = Column(String)
    acertos = Column(Integer)
    nivel = Column(String)
    data = Column(String)