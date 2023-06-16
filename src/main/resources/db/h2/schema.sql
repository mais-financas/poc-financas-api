CREATE TABLE IF NOT EXISTS gestor (
	id uuid PRIMARY KEY,
	nome TEXT NOT NULL,
	email TEXT NOT NULL,
	password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS despesa (
    id serial PRIMARY KEY,
    nome TEXT NOT NULL,
    categoria TEXT NOT NULL,
    data DATE NOT NULL,
    valor numeric(8, 2) NOT NULL,
    gestor_id uuid NOT NULL,

	CONSTRAINT fk_gestor_id
	FOREIGN KEY(gestor_id) REFERENCES gestor(id)
	ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS objetivo (
    id serial PRIMARY KEY,
    nome TEXT NOT NULL,
    prazo_estimado DATE NOT NULL,
    gestor_id uuid NOT NULL,

	FOREIGN KEY(gestor_id) REFERENCES gestor(id)
	ON DELETE CASCADE
);