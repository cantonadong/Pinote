# Repository workflow

- Use `main` as the only branch. Do not create release, feature, or temporary branches.
- Before editing, fetch remote state and make local `main` exactly match `origin/main`, while safely preserving any uncommitted local work.
- Commit and push every project file required to build and run the app. Never commit private signing keys, local SDK paths, build outputs, or machine-specific files.
- After pushing, verify that local `main` and `origin/main` resolve to the same commit and that the working tree contains no uncommitted project changes.
- Version releases with Git tags on `main`; do not use version branches.
