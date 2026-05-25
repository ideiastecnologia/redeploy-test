# V2 — Versão com erro de compilação (para testar o redeploy)

Aplique UMA destas alterações, commite e faça o redeploy no CloudPilot.
O build da V2 vai FALHAR. O teste é confirmar que a V1 continua no ar.

## Opção mais simples — quebrar o Controller

Edite `src/main/java/com/ideiashub/redeploytest/LeadController.java` e
troque o método `count()` por esta versão com erro de sintaxe (falta `;` e tipo errado):

```java
    @GetMapping("/count")
    public Map<String, Long> count() {
        return Map.of("total", repository.count())   // <-- ERRO: falta ponto-e-vírgula
        int quebrado = "isso nao compila";            // <-- ERRO: String atribuída a int
    }
```

Commite com: `git commit -am "v2: break build on purpose (redeploy test)"`
Push e faça o redeploy no CloudPilot.

## O que esperar (fix B-REDEPLOY-ROLLBACK-SAFETY)
- Build da V2 FALHA no CodeBuild (erro de compilação).
- A V1 CONTINUA NO AR — URL resolve, dados no banco intactos.
- Onboarding fica FAILED com mensagem "a versão anterior continua no ar".
- Notificação REDEPLOY_FAILED_V1_PRESERVED criada.
- Audit log: REDEPLOY_FAILED_V1_PRESERVED (não ONBOARDING_ROLLED_BACK).

Se a V1 sobreviver → crítico resolvido.
