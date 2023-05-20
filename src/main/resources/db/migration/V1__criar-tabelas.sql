CREATE TABLE gestor (
	id uuid PRIMARY KEY,
	nome TEXT NOT NULL,
	email TEXT NOT NULL,
	password TEXT NOT NULL
);

CREATE TABLE despesa (
    id serial PRIMARY KEY,
    nome TEXT NOT NULL,
    valor numeric(8, 2) NOT NULL,
    gestor_id uuid NOT NULL,

	CONSTRAINT fk_gestor_id
	FOREIGN KEY(gestor_id) REFERENCES gestor(id)
	ON DELETE CASCADE
);