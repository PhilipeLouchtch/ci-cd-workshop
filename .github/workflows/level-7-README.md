# Level 7 — Star Road

#### Doel: Navigeer over de drukke Star Road

#### Daadwerkelijk doel:
- Grote workflows lezen en begrijpen.
- Refactoren naar meerdere, duidelijke jobs met `needs`.
- Hergebruik via composite actions en (optioneel) reusable workflows (`workflow_call`).
- Best practices: duidelijke namen, minimale permissies, conditionals, artifacts, caching, concurrency.

#### Opdracht:
1. Open `.github/workflows/level-7.yml`. Dit is bewust één grote job met alle stappen (build, test, coverage, artifact, image build/push, caching).
2. Draai de workflow handmatig 1x om gevoel te krijgen bij de stappen en logs.
3. Splits de workflow op in meerdere logische jobs.
4. Verminder duplicatie. Factoriseer gedeelde setup (checkout + JDK + caching + Gradle) in een composite action in `.github/actions/...` en gebruik die in alle jobs.
   - Voorbeeld: `.github/actions/setup-java-gradle/action.yml` (zie voor oplossing).
5. Implementeer de coverage gate (zoals in Level 3). Je kan óf het JaCoCo XML parsen, óf Gradle’s `jacocoTestCoverageVerification` gebruiken met een drempel (bijv. via env `JACOCO_MINIMUM_COVERAGE`). Upload altijd de rapporten (`if: always()`).
6. Houd permissies minimaal. Voor GHCR push is `packages: write` nodig. Overweeg permissies op job‑niveau alleen voor de image‑job.
7. Zorg dat caching werkt met stabiele keys op basis van `hashFiles(...)` en dat vervolg runs cache hits laten zien.
8. Push de image alléén op `main` (conditioneel via `if: github.ref == 'refs/heads/main'`).

#### Acceptatiecriteria:
- Functioneel gelijkwaardig aan de monoliet: build + test + coverage gate (≥ drempel) + JAR artifact + image build; push gebeurt alleen op `main`.
- Minder duplicatie door hergebruik (composite action of reusable workflows).
- Workflow is opgesplitst in logische jobs met duidelijke namen en `needs`.
- Caching toont hits op vervolg runs en verkort de buildtijd indicatief.
- Permissies zijn minimaal noodzakelijk; geen onnodige writes.

#### Hints:
- Caching keys:
  - Wrapper: `${{ runner.os }}-gradle-wrapper-${{ hashFiles('**/gradle/wrapper/gradle-wrapper.properties') }}`
  - Caches: `${{ runner.os }}-gradle-caches-${{ hashFiles('**/*.gradle*', '**/gradle/wrapper/gradle-wrapper.properties', '**/gradle/libs.versions.toml') }}`
- Coverage XML pad: `build/reports/jacoco/test/jacocoTestReport.xml` (`<counter type="LINE" missed=".." covered=".."/>`).
- Artifacts uploaden met `actions/upload-artifact@v4` en `if: always()` voor rapporten.
- Overweeg `concurrency` om dubbele runs op dezelfde branch te annuleren.
 - Image build (zoals in de oplossing): download het JAR‑artifact met `actions/download-artifact@v4` naar de `docker/` map en kopieer naar een vaste naam (bijv. `docker/app.jar`) zodat de Dockerfile eenvoudig kan `COPY`‑en.
 - Coverage gate alternatief: stel `JACOCO_MINIMUM_COVERAGE` in (bijv. `0.60`) en laat Gradle falen onder de drempel.