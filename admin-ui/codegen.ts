import type { CodegenConfig } from '@graphql-codegen/cli'

const config: CodegenConfig = {
  schema: '../party/graphql/src/main/resources/graphql/*.graphqls',
  documents: './src/**/*.ts',
  generates: {
    './src/graphql/generated.ts': {
      plugins: ['typescript', 'typescript-operations', 'typescript-apollo-angular']
    }
  },
  config: {
    addExplicitOverride: true
  }
}
export default config
