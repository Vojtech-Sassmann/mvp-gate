import { gql } from 'apollo-angular';
import { Injectable } from '@angular/core';
import * as Apollo from 'apollo-angular';
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
export type MakeEmpty<T extends { [key: string]: unknown }, K extends keyof T> = { [_ in K]?: never };
export type Incremental<T> = T | { [P in keyof T]?: P extends ' $fragmentName' | '__typename' ? T[P] : never };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: { input: string | number; output: string; }
  String: { input: string; output: string; }
  Boolean: { input: boolean; output: boolean; }
  Int: { input: number; output: number; }
  Float: { input: number; output: number; }
};

export type CreateIdpInput = {
  loginUrl: Scalars['String']['input'];
  name: Scalars['String']['input'];
};

export type CreateSepInput = {
  name: Scalars['String']['input'];
  redirectUrls: Array<Scalars['String']['input']>;
};

export type Idp = {
  __typename?: 'Idp';
  id: Scalars['ID']['output'];
  loginUrl: Scalars['String']['output'];
  name: Scalars['String']['output'];
};

export type IdpConnection = {
  __typename?: 'IdpConnection';
  nodes: Array<Idp>;
  totalCount: Scalars['Int']['output'];
};

export type IdpOrder = {
  direction: OrderDirection;
  field: IdpOrderField;
};

export enum IdpOrderField {
  Name = 'NAME'
}

export type Mutation = {
  __typename?: 'Mutation';
  /** Create idp from given input. If the creation fails, null is returned with errors. */
  createIdp?: Maybe<Idp>;
  /** Create sep from given input. If the creation fails, null is returned with errors. */
  createSep?: Maybe<Sep>;
};


export type MutationCreateIdpArgs = {
  createIdpInput: CreateIdpInput;
};


export type MutationCreateSepArgs = {
  createSepInput: CreateSepInput;
};

export enum OrderDirection {
  Asc = 'ASC',
  Desc = 'DESC'
}

export type Query = {
  __typename?: 'Query';
  idps: IdpConnection;
  seps: SepConnection;
};


export type QueryIdpsArgs = {
  orderBy?: InputMaybe<IdpOrder>;
  page: Scalars['Int']['input'];
  size: Scalars['Int']['input'];
};


export type QuerySepsArgs = {
  orderBy: SepOrder;
  page: Scalars['Int']['input'];
  size: Scalars['Int']['input'];
};

export type Sep = {
  __typename?: 'Sep';
  id: Scalars['String']['output'];
  name: Scalars['String']['output'];
  redirectUrls: Array<Scalars['String']['output']>;
};

export type SepConnection = {
  __typename?: 'SepConnection';
  nodes: Array<Sep>;
  totalCount: Scalars['Int']['output'];
};

export type SepOrder = {
  direction: OrderDirection;
  field: SepOrderField;
};

export enum SepOrderField {
  Name = 'NAME'
}

export type CreateIdpMutationVariables = Exact<{
  input: CreateIdpInput;
}>;


export type CreateIdpMutation = { __typename?: 'Mutation', createIdp?: { __typename?: 'Idp', id: string, name: string, loginUrl: string } | null };

export type CreateSepMutationVariables = Exact<{
  input: CreateSepInput;
}>;


export type CreateSepMutation = { __typename?: 'Mutation', createSep?: { __typename?: 'Sep', id: string, name: string, redirectUrls: Array<string> } | null };

export type IdpsQueryVariables = Exact<{
  page: Scalars['Int']['input'];
  size: Scalars['Int']['input'];
  field: IdpOrderField;
  direction: OrderDirection;
}>;


export type IdpsQuery = { __typename?: 'Query', idps: { __typename?: 'IdpConnection', totalCount: number, nodes: Array<{ __typename?: 'Idp', id: string, name: string, loginUrl: string }> } };

export type SepsQueryVariables = Exact<{
  page: Scalars['Int']['input'];
  size: Scalars['Int']['input'];
  field: SepOrderField;
  direction: OrderDirection;
}>;


export type SepsQuery = { __typename?: 'Query', seps: { __typename?: 'SepConnection', totalCount: number, nodes: Array<{ __typename?: 'Sep', id: string, name: string, redirectUrls: Array<string> }> } };

export const CreateIdpDocument = gql`
    mutation CreateIdp($input: CreateIdpInput!) {
  createIdp(createIdpInput: $input) {
    id
    name
    loginUrl
  }
}
    `;

  @Injectable({
    providedIn: 'root'
  })
  export class CreateIdpGQL extends Apollo.Mutation<CreateIdpMutation, CreateIdpMutationVariables> {
    override document = CreateIdpDocument;
    
    constructor(apollo: Apollo.Apollo) {
      super(apollo);
    }
  }
export const CreateSepDocument = gql`
    mutation CreateSep($input: CreateSepInput!) {
  createSep(createSepInput: $input) {
    id
    name
    redirectUrls
  }
}
    `;

  @Injectable({
    providedIn: 'root'
  })
  export class CreateSepGQL extends Apollo.Mutation<CreateSepMutation, CreateSepMutationVariables> {
    override document = CreateSepDocument;
    
    constructor(apollo: Apollo.Apollo) {
      super(apollo);
    }
  }
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

  @Injectable({
    providedIn: 'root'
  })
  export class IdpsGQL extends Apollo.Query<IdpsQuery, IdpsQueryVariables> {
    override document = IdpsDocument;
    
    constructor(apollo: Apollo.Apollo) {
      super(apollo);
    }
  }
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

  @Injectable({
    providedIn: 'root'
  })
  export class SepsGQL extends Apollo.Query<SepsQuery, SepsQueryVariables> {
    override document = SepsDocument;
    
    constructor(apollo: Apollo.Apollo) {
      super(apollo);
    }
  }