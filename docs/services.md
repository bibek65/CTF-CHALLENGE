# Service Configuration Notes

## Payment Service

Payment service config is managed separately and should **not** be committed to this repo.
Use AWS Secrets Manager or environment variables for all credentials.

> **Note:** Commit `13e14a0` accidentally included `payment-service/src/main/resources/application.yml`
> with production credentials. It was removed immediately. If you need the config template,
> ask the DevOps team — do not add it back to version control.

## Main App

Config lives in `src/main/resources/` — `application.yml` and `application-local.yml` are
gitignored at that path. Keep it that way.
