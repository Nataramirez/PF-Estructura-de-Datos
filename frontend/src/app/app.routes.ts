import { Routes } from '@angular/router';

export const routesCollection = {
    HOME: '',
    MAIN_USER: 'usuario',
    USER_BOOKS: '',
    USER_LOANS: 'prestamos',
    USER_SUGGESTIONS: 'sugerencias',
    USER_RECOMMENDATIONS: 'recomendaciones',
    USER_MESSAGES: 'mensajes',
    MAIN_ADMIN: 'administrador',
    ADMIN_BOOKS: '',
    ADMIN_USERS: 'usuarios',
    ADMIN_AFFINITY: 'afinidad',
    ADMIN_STADISTICS: 'estadisticas',
}

export const routes: Routes = [
    {
        path: routesCollection.HOME,
        loadComponent: () => import('./components/navigation/main-navigation/main-navigation.component').then(m => m.MainNavigationComponent)
    },
    {
        path: routesCollection.MAIN_USER,
        loadComponent: () => import('./components/navigation/main-user/main-user.component').then(m => m.MainUserComponent),
        children: [
            {
                path: '',
                loadComponent: () => import('./components/books/books.component').then(m => m.BooksComponent),
            },
            {
                path: routesCollection.USER_LOANS,
                loadComponent: () => import('./components/loans/loans.component').then(m => m.LoansComponent),
            },
            {
                path: routesCollection.USER_RECOMMENDATIONS,
                loadComponent: () => import('./components/recommendations/recommendations.component').then(m => m.RecommendationsComponent),
            },
            {
                path: routesCollection.USER_SUGGESTIONS,
                loadComponent: () => import('./components/suggestions/suggestions.component').then(m => m.SuggestionsComponent),
            },
            {
                path: routesCollection.USER_MESSAGES,
                loadComponent: () => import('./components/messages/messages.component').then(m => m.MessagesComponent),
            }
        ]
    },
    {
        path: routesCollection.MAIN_ADMIN,
        loadComponent: () => import('./components/navigation/main-admin/main-admin.component').then(m => m.MainAdminComponent),
        children: [
            {
                path: routesCollection.ADMIN_BOOKS,
                loadComponent: () => import('./components/admin-panel-books/books-admin-panel.component').then(m => m.BooksAdminPanelComponent),
            },
            {
                path: routesCollection.ADMIN_USERS,
                loadComponent: () => import('./components/admin-panel-users/admin-panel-users.component').then(m => m.AdminPanelUsersComponent),
            },
            {
                path: routesCollection.ADMIN_AFFINITY,
                loadComponent: () => import('./components/affinity/affinity.component').then(m => m.AffinityComponent),
            },
            {
                path: routesCollection.ADMIN_STADISTICS,
                loadComponent: () => import('./components/stadistics/stadistics.component').then(m => m.StadisticsComponent),
            }
        ]
    }
];
