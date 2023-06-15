import {gql} from "apollo-angular";

export const CreateIdpDocument = gql`
  mutation CreateIdp($input: CreateIdpInput!) {
    createIdp(createIdpInput: $input) {
      id
      name
      loginUrl
    }
  }`;

export const CreateSepDocument = gql`
  mutation CreateSep($input: CreateSepInput!) {
    createSep(createSepInput: $input) {
      id
      name
      redirectUrls
    }
  }`;

export const IdpsDocument = gql`
  query Idps($page: Int!, $size: Int!, $field: IdpOrderField!, $direction: OrderDirection!) {
    idps(page: $page, size: $size, orderBy: {field: $field, direction: $direction}) {
      nodes {
        id
        name
        loginUrl
      }
      totalCount
    }
  }
`;

export const SepsDocument = gql`
  query Seps($page: Int!, $size: Int!, $field: SepOrderField!, $direction: OrderDirection!) {
    seps(page: $page, size: $size, orderBy: {field: $field, direction: $direction}) {
      nodes {
        id
        name
        redirectUrls
      }
      totalCount
    }
  }
`;
