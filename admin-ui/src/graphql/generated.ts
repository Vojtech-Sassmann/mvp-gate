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
  field?: InputMaybe<IdpOrderField>;
};

export enum IdpOrderField {
  Name = 'NAME'
}

export type Mutation = {
  __typename?: 'Mutation';
  /** Create idp from given input. If the creation fails, null is returned with errors. */
  createIdp?: Maybe<Idp>;
};


export type MutationCreateIdpArgs = {
  createIdpInput?: InputMaybe<CreateIdpInput>;
};

export enum OrderDirection {
  Asc = 'ASC',
  Desc = 'DESC'
}

export type Query = {
  __typename?: 'Query';
  idps: IdpConnection;
};


export type QueryIdpsArgs = {
  orderBy?: InputMaybe<IdpOrder>;
  page: Scalars['Int']['input'];
  size: Scalars['Int']['input'];
};

export type IdpsQueryVariables = Exact<{
  page: Scalars['Int']['input'];
  size: Scalars['Int']['input'];
  field?: InputMaybe<IdpOrderField>;
  direction: OrderDirection;
}>;


export type IdpsQuery = { __typename?: 'Query', idps: { __typename?: 'IdpConnection', totalCount: number, nodes: Array<{ __typename?: 'Idp', id: string, name: string, loginUrl: string }> } };

export const IdpsDocument = gql`
    query Idps($page: Int!, $size: Int!, $field: IdpOrderField, $direction: OrderDirection!) {
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