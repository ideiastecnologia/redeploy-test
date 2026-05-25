# redeploy-test

App de teste (Spring Boot + Flyway + PostgreSQL) para validar o ciclo de redeploy do CloudPilot.

- Formulário de leads + dashboard com contador
- Flyway roda a migration `V1__create_leads.sql` no startup (Modelo A)
- Banco provisionado pelo CloudPilot (pool ou dedicated)
- Health check: `/actuator/health`

## Teste de redeploy
1. Publicar V1 → app sobe, Flyway cria tabela `leads`, formulário grava.
2. Aplicar a alteração quebrada da V2 (ver branch/commit `break-v2`) → redeploy.
3. Confirmar que a V1 continua no ar (fix B-REDEPLOY-ROLLBACK-SAFETY).
