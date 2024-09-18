Create table Pessoa (Id serial PRIMARY KEY, Nome VARCHAR(100) NOT NULL,
Cpf VARCHAR(11) NOT NULL UNIQUE,DataNascimento Date not null);

Create table Funcionario (SalarioBruto VARCHAR(10),
DescontoInss VARCHAR(10) , DescontoIR VARCHAR(10),
SalarioLiquido VARCHAR(10) NOT NULL, Pessoa_Id int, foreign key(Pessoa_Id) references Pessoa(Id));

create table Dependente (Parentesco varchar(15), Data date not null, 
Pessoa_Id int, foreign key(Pessoa_Id) references Pessoa(Id));